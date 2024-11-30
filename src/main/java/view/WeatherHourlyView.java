package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.login.LoginViewModel;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather_daily.WeatherDailyViewModel;
import interface_adapter.weather_hourly.WeatherHourlyController;
import interface_adapter.weather_hourly.WeatherHourlyState;
import interface_adapter.weather_hourly.WeatherHourlyViewModel;

/**
 * The View for when the user is on Weather Use Case.
 */
public class WeatherHourlyView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "weather hourly";
    private final WeatherHourlyViewModel weatherHourlyViewModel;
    private WeatherHourlyController weatherController;

    // UI components
    private final JTextField cityNameInputField = new JTextField(15);
    private final JTextField hourInputField = new JTextField(9);

    private final JLabel cityLabel = new JLabel(WeatherHourlyViewModel.CITY_INFO_LABEL);
    private final JLabel temperatureLabel = new JLabel(WeatherHourlyViewModel.TEMPERATURE_LABEL);
    private final JLabel conditionLabel = new JLabel(WeatherHourlyViewModel.CONDITION_LABEL);
    private final JLabel errorMessageLabel = new JLabel();

    private final JLabel backGround = new JLabel();

    private final JButton getWeatherButton;
    private final JButton goToSettingsButton;

    private final JPanel mainPanel = new JPanel();
    private final JLayeredPane layeredPane = new JLayeredPane();

    // Constructor
    public WeatherHourlyView(WeatherHourlyViewModel weatherViewModel) {

        this.weatherHourlyViewModel = weatherViewModel;
        this.weatherHourlyViewModel.addPropertyChangeListener(this);

        // set the initial background picture to "clear".
        final ImageIcon initialImage = new ImageIcon("resources/images/weather_conditions/clear.jpg");
        final ImageIcon scaledIcon = getScaledIcon(initialImage);
        backGround.setIcon(scaledIcon);
        backGround.setBounds(0, 0, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());

        layeredPane.setSize(scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
        layeredPane.add(backGround, JLayeredPane.DEFAULT_LAYER);

        final JLabel title = new JLabel(WeatherHourlyViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create input field for city name.
        final LabelTextPanel cityInputPanel = new LabelTextPanel(
                new JLabel(WeatherHourlyViewModel.CITY_LABEL), cityNameInputField);
        cityInputPanel.setOpaque(false);

        final LabelTextPanel hourInputPanel = new LabelTextPanel(
                new JLabel(WeatherHourlyViewModel.HOUR_LABEL), hourInputField
        );
        hourInputPanel.setOpaque(false);

        // Create drop-down menu to switch between modes.
        final String[] modeOptions = new String[] {WeatherHourlyViewModel.CURRENT,
                                                   WeatherHourlyViewModel.HOURLY,
                                                   WeatherHourlyViewModel.DAILY};
        final JComboBox modeComboBox = new JComboBox(modeOptions);
        modeComboBox.setSelectedItem(WeatherDailyViewModel.HOURLY);
        modeComboBox.setPreferredSize(new Dimension(75, 30));
        modeComboBox.setMaximumSize(new Dimension(75, 30));
        modeComboBox.setOpaque(false);

        final JPanel weatherInfoPanel = new JPanel();
        weatherInfoPanel.setLayout(new BoxLayout(weatherInfoPanel, BoxLayout.Y_AXIS));
        weatherInfoPanel.add(cityLabel);
        weatherInfoPanel.add(temperatureLabel);
        weatherInfoPanel.add(conditionLabel);
        weatherInfoPanel.add(errorMessageLabel);
        weatherInfoPanel.setOpaque(false);

        final JPanel buttons = new JPanel();
        getWeatherButton = new JButton(WeatherHourlyViewModel.GET_WEATHER_BUTTON_LABEL);
        buttons.add(getWeatherButton);
        goToSettingsButton = new JButton(WeatherHourlyViewModel.SETTINGS_LABEL);
        buttons.add(goToSettingsButton);
        buttons.setOpaque(false);

        // Add action listener to the "Get Weather" button
        getWeatherButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final String cityName = cityNameInputField.getText();
                        final String hour = hourInputField.getText();
                        weatherController.execute(cityName, hour);
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
                        if (mode.equals(WeatherHourlyViewModel.CURRENT)) {
                            weatherController.switchToCurrView();
                        }
                        else if (mode.equals(WeatherHourlyViewModel.DAILY)) {
                            weatherController.switchToDailyView();
                        }
                        modeComboBox.setSelectedItem(WeatherDailyViewModel.HOURLY);
                    }
                }
        );

        mainPanel.setOpaque(false);
        mainPanel.setBounds(0, 0, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
        setMainLayout(mainPanel, title, cityInputPanel, hourInputPanel, modeComboBox, buttons, weatherInfoPanel);

        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        this.setLayout(null);
        this.add(layeredPane);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    // Note that this method is un used thus can be deleted.
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Settings not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final WeatherHourlyState state = (WeatherHourlyState) evt.getNewValue();
        setFields(state);
        if (state.getErrorMessage() != null) {
            errorMessageLabel.setText(state.getErrorMessage());
            JOptionPane.showMessageDialog(this, errorMessageLabel);
        }
    }

    private void setMainLayout(JPanel panel,
                               JLabel title,
                               JPanel cityInputPanel,
                               JPanel hourInputPanel,
                               JComboBox<String> modeComboBox,
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
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(cityInputPanel)
                                        .addComponent(hourInputPanel))
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
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(cityInputPanel)
                                        .addComponent(hourInputPanel))
                                .addComponent(modeComboBox))
                        .addComponent(buttons)
                        .addComponent(weatherInfoPanel)
                        .addGap(100)
        );
    }

    private void setFields(WeatherHourlyState state) {
        backGround.setIcon(getUpdatedIcon(state));
        cityNameInputField.setText("");
        cityLabel.setText(WeatherHourlyViewModel.CITY_INFO_LABEL + state.getCity());
        temperatureLabel.setText(WeatherHourlyViewModel.TEMPERATURE_LABEL + state.getTemperature());
        conditionLabel.setText(WeatherHourlyViewModel.CONDITION_LABEL + state.getCondition());
    }

    private Icon getUpdatedIcon(WeatherHourlyState state) {
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

    public void setWeatherController(WeatherHourlyController weatherController) {
        this.weatherController = weatherController;
    }
}
