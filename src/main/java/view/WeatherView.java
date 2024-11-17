package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;

public class WeatherView extends JPanel implements PropertyChangeListener {

    private final WeatherViewModel weatherViewModel;

    // UI components
    private final JTextField citynameInputField = new JTextField(15);
    private final JLabel cityLabel = new JLabel("City: ");
    private final JLabel temperatureLabel = new JLabel("Temperature: ");
    private final JLabel conditionLabel = new JLabel("Condition: ");
    private final JLabel errorMessageLabel = new JLabel();
    private final JButton getWeatherButton;

    // Constructor
    public WeatherView(WeatherViewModel weatherViewModel) {
        this.weatherViewModel = weatherViewModel;
        this.weatherViewModel.getPropertyChangeSupport().addPropertyChangeListener(this);

        final JLabel title = new JLabel("Weather Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel cityInputPanel = new LabelTextPanel(
                new JLabel("City Name"), citynameInputField);

        final JPanel weatherInfoPanel = new JPanel();
        weatherInfoPanel.setLayout(new BoxLayout(weatherInfoPanel, BoxLayout.Y_AXIS));
        weatherInfoPanel.add(cityLabel);
        weatherInfoPanel.add(temperatureLabel);
        weatherInfoPanel.add(conditionLabel);
        weatherInfoPanel.add(errorMessageLabel);

        getWeatherButton = new JButton("Get Weather");

        // Set up the main layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(cityInputPanel);
        this.add(getWeatherButton);
        this.add(weatherInfoPanel);

        // Add action listener to the "Get Weather" button
        getWeatherButton.addActionListener(evt -> {
            String cityName = citynameInputField.getText();
            weatherViewModel.fetchWeatherData(cityName);
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("weatherState")) {
            WeatherState state = (WeatherState) evt.getNewValue();

            cityLabel.setText("City: " + state.getCity());
            temperatureLabel.setText("Temperature: " + state.getTemperature());
            conditionLabel.setText("Condition: " + state.getCondition());
            errorMessageLabel.setText(state.getErrorMessage() == null ? "" : "Error: " + state.getErrorMessage());
        }
    }
}
