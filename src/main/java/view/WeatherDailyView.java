package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

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
    private final JLabel cityLabel = new JLabel(WeatherDailyViewModel.CITY_INFO_LABEL);
    private final JTextField dayInputField = new JTextField(15);
    private final JLabel dayLabel = new JLabel(WeatherDailyViewModel.DAY_LABEL);
    private final JLabel temperatureLabel = new JLabel(WeatherDailyViewModel.TEMPERATURE_LABEL);
    private final JLabel conditionLabel = new JLabel(WeatherDailyViewModel.CONDITION_LABEL);
    private final JLabel summaryLabel = new JLabel(WeatherDailyViewModel.SUMMARY_LABEL);
    private final JLabel errorMessageLabel = new JLabel();
    private final JButton getWeatherButton;
    private final JButton goToSettingsButton;

    // Constructor.
    public WeatherDailyView(WeatherDailyViewModel weatherDailyViewModel) {

        this.weatherDailyViewModel = weatherDailyViewModel;
        this.weatherDailyViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(WeatherDailyViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create input field for city name.
        final LabelTextPanel cityInputPanel = new LabelTextPanel(
                new JLabel(WeatherDailyViewModel.CITY_LABEL), cityNameInputField
        );

        // Create input field for day num.
        final JPanel dayInputPanel = new JPanel();
        dayInputPanel.setLayout(new BoxLayout(dayInputPanel, BoxLayout.Y_AXIS));
        dayInputPanel.add(dayLabel);
        dayInputField.setText("0");
        dayInputPanel.add(dayInputField);

        // Create drop-down menu to switch between modes.
        final String[] modeOptions = new String[] {WeatherDailyViewModel.CURRENT,
                                                   WeatherDailyViewModel.HOURLY,
                                                   WeatherDailyViewModel.DAILY};
        final JComboBox modeComboBox = new JComboBox(modeOptions);
        modeComboBox.setSelectedItem(WeatherDailyViewModel.DAILY);

        // Create a panel to combine city input field and the drop-down menu.
        final JPanel cityInputPlusModePanel = new JPanel();
        cityInputPlusModePanel.setLayout(new BoxLayout(cityInputPlusModePanel, BoxLayout.X_AXIS));
        cityInputPlusModePanel.add(cityInputPanel);
        cityInputPlusModePanel.add(modeComboBox);

        final JPanel weatherInfoPanel = new JPanel();
        weatherInfoPanel.setLayout(new BoxLayout(weatherInfoPanel, BoxLayout.Y_AXIS));
        weatherInfoPanel.add(cityLabel);
        weatherInfoPanel.add(temperatureLabel);
        weatherInfoPanel.add(conditionLabel);
        weatherInfoPanel.add(summaryLabel);
        weatherInfoPanel.add(errorMessageLabel);

        final JPanel buttons = new JPanel();
        getWeatherButton = new JButton(WeatherDailyViewModel.GET_WEATHER_BUTTON_LABEL);
        buttons.add(getWeatherButton);
        goToSettingsButton = new JButton(WeatherDailyViewModel.SETTINGS_LABEL);
        buttons.add(goToSettingsButton);

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

        // Set up the main layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(cityInputPlusModePanel);
        this.add(dayInputPanel);
        this.add(buttons);
        this.add(weatherInfoPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
        cityNameInputField.setText("");
        cityLabel.setText(WeatherDailyViewModel.CITY_INFO_LABEL + state.getCity());
        temperatureLabel.setText(WeatherDailyViewModel.TEMPERATURE_LABEL + state.getTemperature());
        conditionLabel.setText(WeatherDailyViewModel.CONDITION_LABEL + state.getCondition());
        summaryLabel.setText(WeatherDailyViewModel.SUMMARY_LABEL + state.getSummary());
    }

    public String getViewName() {
        return viewName;
    }

    public void setWeatherDailyController(WeatherDailyController weatherDailyController) {
        this.weatherDailyController = weatherDailyController;
    }

}
