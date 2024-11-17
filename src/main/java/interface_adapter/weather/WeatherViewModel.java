package interface_adapter.weather;

import interface_adapter.ViewModel;
import use_case.weather.WeatherInputBoundary;
import use_case.weather.WeatherInputData;
import java.beans.PropertyChangeSupport;

public class WeatherViewModel extends ViewModel<WeatherState> {

    private final WeatherInputBoundary weatherInteractor;
    private final PropertyChangeSupport propertyChangeSupport;

    // Constructor that takes the WeatherInteractor
    public WeatherViewModel(WeatherInputBoundary weatherInteractor) {
        super("weather");
        this.weatherInteractor = weatherInteractor;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        setState(new WeatherState());
    }

    // Method to initiate fetching weather data
    public void fetchWeatherData(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            updateStateWithError("Invalid City.");
            return;
        }

        // Create a WeatherInputData object and pass it to the interactor
        WeatherInputData inputData = new WeatherInputData(cityName);
        weatherInteractor.execute(inputData);
    }

    // Method to update the state with weather data (to be called by the presenter)
    public void updateStateWithWeatherData(String city, String temperature, String condition) {
        WeatherState state = getState();
        state.setCity(city);
        state.setTemperature(temperature);
        state.setCondition(condition);
        setState(state);
        propertyChangeSupport.firePropertyChange("weatherState", null, state);
    }

    // Method to update the state with an error message (to be called by the presenter)
    public void updateStateWithError(String errorMessage) {
        WeatherState state = getState();
        state.setErrorMessage(errorMessage);
        setState(state);
        propertyChangeSupport.firePropertyChange("weatherState", null, state);
    }

    // Getter for PropertyChangeSupport
    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
}

