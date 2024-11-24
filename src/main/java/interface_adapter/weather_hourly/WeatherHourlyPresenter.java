package interface_adapter.weather_hourly;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.weather.WeatherViewModel;
import use_case.weather_hourly.WeatherHourlyOutputBoundary;
import use_case.weather_hourly.WeatherHourlyOutputData;

/**
 * The Presenter for the Weather Use Case.
 */
public class WeatherHourlyPresenter implements WeatherHourlyOutputBoundary {

    private final WeatherViewModel weatherViewModel;
    private final WeatherHourlyViewModel weatherHourlyViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public WeatherHourlyPresenter(ViewManagerModel viewManagerModel,
                                  WeatherViewModel weatherViewModel,
                                  WeatherHourlyViewModel weatherHourlyViewModel,
                                  LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.weatherViewModel = weatherViewModel;
        this.weatherHourlyViewModel = weatherHourlyViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(WeatherHourlyOutputData outputData) {
        // On success, update the WeatherViewModel with the received weather data.
        final WeatherHourlyState weatherHourlyState = weatherHourlyViewModel.getState();
        weatherHourlyState.setCity(outputData.getCity());
        weatherHourlyState.setTemperature(outputData.getTemperature());
        weatherHourlyState.setCondition(outputData.getCondition());
        weatherHourlyState.setErrorMessage(null);
        weatherHourlyViewModel.setState(weatherHourlyState);

        weatherHourlyViewModel.firePropertyChanged();

        viewManagerModel.setState(weatherHourlyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        // On failure, update the WeatherViewModel with an error message.
        final WeatherHourlyState weatherState = weatherHourlyViewModel.getState();
        weatherState.setErrorMessage(error);
        weatherHourlyViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToCurrView() {
        viewManagerModel.setState(weatherViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToDailyView() {
//        viewManagerModel.setState();
        viewManagerModel.firePropertyChanged();
    }

}
