package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.login.LoginViewModel;
import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;
import interface_adapter.weather_daily.WeatherDailyViewModel;

/**
 * The View for when the user is on Weather Use Case.
 */
public class WeatherView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "weather";
    private final WeatherViewModel weatherViewModel;
    private WeatherController weatherController;

    // UI components
    private final JTextField cityNameInputField = new JTextField(15);

    private final JLabel cityLabel = new JLabel(WeatherViewModel.CITY_INFO_LABEL);
    private final JLabel temperatureLabel = new JLabel(WeatherViewModel.TEMPERATURE_LABEL);
    private final JLabel descriptionLabel = new JLabel(WeatherViewModel.DESCRIPTION_LABEL);
    private final JLabel errorMessageLabel = new JLabel();

    private final JLabel backGround = new JLabel();

    private final JButton getWeatherButton;
    private final JButton goToSettingsButton;

    private final JPanel mainPanel = new JPanel();
    private final JLayeredPane layeredPane = new JLayeredPane();

    // Constructor
    public WeatherView(WeatherViewModel weatherViewModel) {

        this.weatherViewModel = weatherViewModel;
        this.weatherViewModel.addPropertyChangeListener(this);

        // set the initial background picture to "clear".
        final ImageIcon initialImage = new ImageIcon("resources/images/weather_conditions/clear.jpg");
        final ImageIcon scaledIcon = getScaledIcon(initialImage);
        backGround.setIcon(scaledIcon);
        backGround.setBounds(0, 0, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());

        layeredPane.setSize(scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
        layeredPane.add(backGround, JLayeredPane.DEFAULT_LAYER);

        // title of this view
        final JLabel title = new JLabel(WeatherViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create input field for city name.
        final LabelTextPanel cityInputPanel = new LabelTextPanel(
                new JLabel(WeatherViewModel.CITY_LABEL), cityNameInputField);
        cityInputPanel.setOpaque(false);

        // Create drop-down menu to switch between modes.
        final String[] modeOptions = new String[] {WeatherViewModel.CURRENT,
                                                   WeatherViewModel.HOURLY,
                                                   WeatherViewModel.DAILY};
        final JComboBox<String> modeComboBox = new JComboBox<>(modeOptions);
        modeComboBox.setSelectedItem(WeatherDailyViewModel.CURRENT);
        modeComboBox.setPreferredSize(new Dimension(75, 30));
        modeComboBox.setMaximumSize(new Dimension(75, 30));
        modeComboBox.setOpaque(false);

        final JPanel weatherInfoPanel = new JPanel();
        weatherInfoPanel.setLayout(new BoxLayout(weatherInfoPanel, BoxLayout.Y_AXIS));
        weatherInfoPanel.add(cityLabel);
        weatherInfoPanel.add(temperatureLabel);
        weatherInfoPanel.add(descriptionLabel);
        weatherInfoPanel.add(errorMessageLabel);
        weatherInfoPanel.setOpaque(false);

        final JPanel buttons = new JPanel();
        getWeatherButton = new JButton(WeatherViewModel.GET_WEATHER_BUTTON_LABEL);
        buttons.add(getWeatherButton);
        goToSettingsButton = new JButton(WeatherViewModel.SETTINGS_LABEL);
        buttons.add(goToSettingsButton);
        buttons.setOpaque(false);

        // Add action listener to the "Get Weather" button
        getWeatherButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final String cityName = cityNameInputField.getText();
                        weatherController.execute(cityName);
                    }
                });

        goToSettingsButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        weatherController.switchToLoggedInView();
                    }
                });

        modeComboBox.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final String mode = (String) modeComboBox.getSelectedItem();
                        if (mode.equals(WeatherViewModel.HOURLY)) {
                            weatherController.switchToHourlyView();
                        }
                        else if (mode.equals(WeatherViewModel.DAILY)) {
                            weatherController.switchToDailyView();
                        }
                        modeComboBox.setSelectedItem(WeatherDailyViewModel.CURRENT);
                    }
                }
        );

        mainPanel.setOpaque(false);
        mainPanel.setBounds(0, 0, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
        setMainLayout(mainPanel, title, cityInputPanel, modeComboBox, buttons, weatherInfoPanel);

        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        this.setLayout(null);
        this.add(layeredPane);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final WeatherState state = (WeatherState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            errorMessageLabel.setText(state.getErrorMessage());
            JOptionPane.showMessageDialog(this, errorMessageLabel);
        }
        setFields(state);
    }

    private void setMainLayout(JPanel panel,
                               JLabel title,
                               JPanel cityInputPanel,
                               JComboBox modeComboBox,
                               JPanel buttons,
                               JPanel weatherInfoPanel) {
        final GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(false);
        layout.setAutoCreateContainerGaps(false);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(title)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(cityInputPanel)
                                .addComponent(modeComboBox)
                                .addGap(40))
                        .addComponent(buttons)
                        .addComponent(weatherInfoPanel)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(title)
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(cityInputPanel)
                                .addComponent(modeComboBox))
                        .addComponent(buttons)
                        .addComponent(weatherInfoPanel)
                        .addGap(100)
        );
    }

    private void setFields(WeatherState state) {
        backGround.setIcon(getUpdatedIcon(state));
        cityNameInputField.setText("");
        cityLabel.setText(WeatherViewModel.CITY_INFO_LABEL + state.getCity());
        temperatureLabel.setText(WeatherViewModel.TEMPERATURE_LABEL + state.getTemperature());
        descriptionLabel.setText(WeatherViewModel.DESCRIPTION_LABEL + state.getDescription());
    }

    private Icon getUpdatedIcon(WeatherState state) {
        final String condition = state.getCondition();
        ImageIcon initialImage = new ImageIcon();

        if ("Thunderstorm".equals(condition)) {
            initialImage =
                    new ImageIcon("resources/images/weather_conditions/thunderstorm.jpg");
        }
        else if ("Drizzle".equals(condition)
                || "Atmosphere".equals(condition)
                || "Clouds".equals(condition)) {
            initialImage = new ImageIcon("resources/images/weather_conditions/clouds.jpg");
        }
        else if ("Rain".equals(condition)) {
            initialImage = new ImageIcon("resources/images/weather_conditions/rain.jpg");
        }
        else if ("Snow".equals(condition)) {
            initialImage = new ImageIcon("resources/images/weather_conditions/snow.jpg");
        }
        else if ("Clear".equals(condition)) {
            initialImage = new ImageIcon("resources/images/weather_conditions/clear.jpg");
        }
        return getScaledIcon(initialImage);
    }

    private static ImageIcon getScaledIcon(ImageIcon initialImage) {
        final Image originalImage = initialImage.getImage();
        final Image scaledImage = originalImage.getScaledInstance(LoginViewModel.WINDOWITH,
                                                                  LoginViewModel.WINDOWITH,
                                                                  Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public String getViewName() {
        return viewName;
    }

    public void setWeatherController(WeatherController weatherController) {
        this.weatherController = weatherController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // we will never use this actionPerformed function.
    }
}
