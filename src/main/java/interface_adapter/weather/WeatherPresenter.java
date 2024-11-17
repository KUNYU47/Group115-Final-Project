package interface_adapter.weather;

import use_case.weather.WeatherOutputBoundary;
import use_case.weather.WeatherOutputData;

public class WeatherPresenter implements WeatherOutputBoundary {

    private final WeatherViewModel weatherViewModel;

    public WeatherPresenter(WeatherViewModel weatherViewModel) {
        this.weatherViewModel = weatherViewModel;
    }

    @Override
    public void prepareSuccessView(WeatherOutputData response) {
        // On success, update the WeatherViewModel with the received weather data.
        final WeatherState weatherState = weatherViewModel.getState();
        weatherState.setCity(response.getCity());
        weatherState.setTemperature(response.getTemperature());
        weatherState.setCondition(response.getCondition());
        weatherViewModel.setState(weatherState);
        weatherViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // On failure, update the WeatherViewModel with an error message.
        final WeatherState weatherState = weatherViewModel.getState();
        weatherState.setErrorMessage(error);
        weatherViewModel.firePropertyChanged();
    }
}
