package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.login.LoginViewModel;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;
import interface_adapter.weather_daily.WeatherDailyController;
import interface_adapter.weather_daily.WeatherDailyState;
import interface_adapter.weather_daily.WeatherDailyViewModel;

public class WeatherDailyView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "weather daily";
    private final WeatherDailyViewModel weatherDailyViewModel;
    private WeatherDailyController weatherDailyController;

    // UI components.
    private final JTextField cityNameInputField = new JTextField(15);
    private final JTextField dayInputField = new JTextField(11);

    private final JLabel cityLabel = new JLabel(WeatherDailyViewModel.CITY_INFO_LABEL);
    private final JLabel temperatureLabel = new JLabel(WeatherDailyViewModel.TEMPERATURE_LABEL);
    private final JLabel conditionLabel = new JLabel(WeatherDailyViewModel.CONDITION_LABEL);
    private final JLabel errorMessageLabel = new JLabel();

    private JComboBox<String> modeComboBox = new JComboBox<>();

    private final JTextArea summaryArea = new JTextArea(WeatherDailyViewModel.SUMMARY_LABEL);

    private final JLabel backGround = new JLabel();

    private final JButton getWeatherButton;
    private final JButton goToSettingsButton;

    private final JPanel mainPanel = new JPanel();
    private final JLayeredPane layeredPane = new JLayeredPane();

    // Constructor.
    public WeatherDailyView(WeatherDailyViewModel weatherDailyViewModel) {

        this.weatherDailyViewModel = weatherDailyViewModel;
        this.weatherDailyViewModel.addPropertyChangeListener(this);

        // set the initial background picture to "clear".
        final ImageIcon initialImage = new ImageIcon("resources/images/weather_conditions/clear.jpg");
        final ImageIcon scaledIcon = getScaledIcon(initialImage);
        backGround.setIcon(scaledIcon);
        backGround.setBounds(0, 0, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());

        layeredPane.setSize(scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
        layeredPane.add(backGround, JLayeredPane.DEFAULT_LAYER);

        final JLabel title = new JLabel(WeatherDailyViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create input field for city name.
        final LabelTextPanel cityInputPanel = new LabelTextPanel(
                new JLabel(WeatherDailyViewModel.CITY_LABEL), cityNameInputField
        );
        cityInputPanel.setOpaque(false);

        final LabelTextPanel dayInputPanel = new LabelTextPanel(
                new JLabel(WeatherDailyViewModel.DAY_LABEL), dayInputField
        );
        dayInputPanel.setOpaque(false);

        // Create drop-down menu to switch between modes.
        final String[] modeOptions = weatherDailyViewModel.getModeOptions();
        modeComboBox = new JComboBox<>(modeOptions);
        modeComboBox.setSelectedItem(WeatherDailyViewModel.DAILY);
        modeComboBox.setPreferredSize(new Dimension(75, 30));
        modeComboBox.setMaximumSize(new Dimension(75, 30));
        modeComboBox.setOpaque(false);

        summaryArea.setTabSize(10);
        summaryArea.setWrapStyleWord(true);
        summaryArea.setLineWrap(true);
        summaryArea.setEditable(false);
        summaryArea.setFont(new Font("Arial", Font.BOLD, 12));
        summaryArea.setOpaque(false);

        final JPanel weatherInfoPanel = new JPanel();
        weatherInfoPanel.setLayout(new BoxLayout(weatherInfoPanel, BoxLayout.Y_AXIS));
        weatherInfoPanel.add(cityLabel);
        weatherInfoPanel.add(temperatureLabel);
        weatherInfoPanel.add(conditionLabel);
        weatherInfoPanel.add(errorMessageLabel);
        weatherInfoPanel.setOpaque(false);

        final JPanel buttons = new JPanel();
        getWeatherButton = new JButton(WeatherDailyViewModel.GET_WEATHER_BUTTON_LABEL);
        buttons.add(getWeatherButton);
        goToSettingsButton = new JButton(WeatherDailyViewModel.SETTINGS_LABEL);
        buttons.add(goToSettingsButton);
        buttons.setOpaque(false);

        // Add action listener to the "Get Weather" button
        getWeatherButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final String cityName = cityNameInputField.getText();
                        final String day = dayInputField.getText();
                        weatherDailyController.execute(cityName, day);
                    }
                });

        goToSettingsButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        weatherDailyController.switchToLoggedInView();
                    }
                });

        modeComboBox.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final String mode = (String) modeComboBox.getSelectedItem();
                        if (mode.equals(WeatherViewModel.HOURLY)) {
                            weatherDailyController.switchToHourlyView();
                        }
                        else if (mode.equals(WeatherViewModel.CURRENT)) {
                            weatherDailyController.switchToCurrView();
                        }
                        modeComboBox.setSelectedItem(WeatherDailyViewModel.DAILY);
                    }
                }
        );

        mainPanel.setOpaque(false);
        mainPanel.setBounds(0, 0, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
        setMainLayout(mainPanel, title,
                      cityInputPanel, dayInputPanel,
                      buttons, weatherInfoPanel, summaryArea);

        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        this.setLayout(null);
        this.add(layeredPane);
    }

    private void setMainLayout(JPanel panel,
                               JLabel title,
                               LabelTextPanel cityInputPanel,
                               LabelTextPanel dayInputPanel,
                               JPanel buttons,
                               JPanel weatherInfoPanel,
                               JTextArea summary) {
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
                                        .addComponent(dayInputPanel))
                                .addComponent(this.modeComboBox)
                                .addGap(40))
                        .addComponent(buttons)
                        .addComponent(weatherInfoPanel)
                        .addComponent(summary)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(title)
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(cityInputPanel)
                                        .addComponent(dayInputPanel))
                                .addComponent(this.modeComboBox))
                        .addComponent(buttons)
                        .addComponent(weatherInfoPanel)
                        .addComponent(summary)
                        .addGap(100)
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this function would never be run.
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final WeatherDailyState state = (WeatherDailyState) evt.getNewValue();
        setFields(state);
        if (state.getErrorMessage() != null) {
            errorMessageLabel.setText(state.getErrorMessage());
            JOptionPane.showMessageDialog(this, errorMessageLabel);
        }

    }

    private void setFields(WeatherDailyState state) {
        backGround.setIcon(getUpdatedIcon(state));
        cityNameInputField.setText(state.getCity());
        cityLabel.setText(WeatherDailyViewModel.CITY_INFO_LABEL + state.getCity());
        temperatureLabel.setText(WeatherDailyViewModel.TEMPERATURE_LABEL + state.getTemperature());
        conditionLabel.setText(WeatherDailyViewModel.CONDITION_LABEL + state.getCondition());
        summaryArea.setText(WeatherDailyViewModel.SUMMARY_LABEL + state.getSummary());
    }

    private Icon getUpdatedIcon(WeatherDailyState state) {
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

    public void setWeatherDailyController(WeatherDailyController weatherDailyController) {
        this.weatherDailyController = weatherDailyController;
    }

}
