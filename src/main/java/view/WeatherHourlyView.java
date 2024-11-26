package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

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
    private final JLabel cityLabel = new JLabel(WeatherHourlyViewModel.CITY_INFO_LABEL);
    private final JLabel temperatureLabel = new JLabel(WeatherHourlyViewModel.TEMPERATURE_LABEL);
    private final JLabel conditionLabel = new JLabel(WeatherHourlyViewModel.CONDITION_LABEL);
    private final JLabel errorMessageLabel = new JLabel();
    private final JButton getWeatherButton;
    private final JButton goToSettingsButton;

    // Constructor
    public WeatherHourlyView(WeatherHourlyViewModel weatherViewModel) {

        this.weatherHourlyViewModel = weatherViewModel;
        this.weatherHourlyViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(WeatherHourlyViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create input field for city name.
        final LabelTextPanel cityInputPanel = new LabelTextPanel(
                new JLabel(WeatherHourlyViewModel.CITY_LABEL), cityNameInputField);

        // Create drop-down menu to switch between modes.
        final String[] modeOptions = new String[] {WeatherHourlyViewModel.CURRENT,
                                                   WeatherHourlyViewModel.HOURLY,
                                                   WeatherHourlyViewModel.DAILY};
        final JComboBox modeComboBox = new JComboBox(modeOptions);

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
        weatherInfoPanel.add(errorMessageLabel);

        final JPanel buttons = new JPanel();
        getWeatherButton = new JButton(WeatherHourlyViewModel.GET_WEATHER_BUTTON_LABEL);
        buttons.add(getWeatherButton);
        goToSettingsButton = new JButton(WeatherHourlyViewModel.SETTINGS_LABEL);
        buttons.add(goToSettingsButton);

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
                        if (mode.equals(WeatherHourlyViewModel.CURRENT)) {
                            weatherController.switchToCurrView();
                        }
                        else if (mode.equals(WeatherHourlyViewModel.DAILY)) {
                            weatherController.switchToDailyView();
                            JOptionPane.showMessageDialog(WeatherHourlyView.this,
                                    "Oops! Daily view is not implemented yet.");
                        }
                    }
                }
        );

        // Set up the main layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(cityInputPlusModePanel);
        this.add(buttons);
        this.add(weatherInfoPanel);
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

    private void setFields(WeatherHourlyState state) {
        cityNameInputField.setText("");
        cityLabel.setText(WeatherHourlyViewModel.CITY_INFO_LABEL + state.getCity());
        temperatureLabel.setText(WeatherHourlyViewModel.TEMPERATURE_LABEL + state.getTemperature());
        conditionLabel.setText(WeatherHourlyViewModel.CONDITION_LABEL + state.getCondition());
    }

    public String getViewName() {
        return viewName;
    }

    public void setWeatherController(WeatherHourlyController weatherController) {
        this.weatherController = weatherController;
    }
}
