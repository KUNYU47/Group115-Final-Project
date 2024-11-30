package interface_adapter.weather_daily;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.weather.WeatherViewModel;
import interface_adapter.weather_hourly.WeatherHourlyViewModel;
import use_case.weather_daily.WeatherDailyOutputBoundary;
import use_case.weather_daily.WeatherDailyOutputData;

/**
 * The Presenter for the Weather Daily Use Case.
 */
public class WeatherDailyPresenter implements WeatherDailyOutputBoundary {

    private final WeatherViewModel weatherViewModel;
    private final WeatherDailyViewModel weatherDailyViewModel;
    private final WeatherHourlyViewModel weatherHourlyViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public WeatherDailyPresenter(ViewManagerModel viewManagerModel,
                                 WeatherViewModel weatherViewModel,
                                 WeatherHourlyViewModel weatherHourlyViewModel,
                                 WeatherDailyViewModel weatherDailyViewModel,
                                 LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.weatherViewModel = weatherViewModel;
        this.weatherDailyViewModel = weatherDailyViewModel;
        this.weatherHourlyViewModel = weatherHourlyViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(WeatherDailyOutputData outputData) {
        final WeatherDailyState weatherDailyState = weatherDailyViewModel.getState();
        weatherDailyState.setCity(outputData.getCity());
        weatherDailyState.setTemperature(outputData.getTemperature());
        weatherDailyState.setCondition(outputData.getCondition());
        weatherDailyState.setDescription(outputData.getDescription());
        weatherDailyState.setSummary(outputData.getSummary());
        weatherDailyState.setErrorMessage(null);
        weatherDailyViewModel.setState(weatherDailyState);

        weatherDailyViewModel.firePropertyChanged();

        viewManagerModel.setState(weatherDailyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final WeatherDailyState weatherDailyState = weatherDailyViewModel.getState();
        weatherDailyState.setErrorMessage(errorMessage);
        weatherDailyViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInView() {
        updateLastView(weatherDailyViewModel.getViewName());
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToCurrView() {
        viewManagerModel.setState(weatherViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHourlyView() {
        viewManagerModel.setState(weatherHourlyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    private void updateLastView(String currView) {
        final LoggedInState lastState = loggedInViewModel.getState();
        lastState.setLastView(currView);
        loggedInViewModel.setState(lastState);
        loggedInViewModel.firePropertyChanged();
    }
}
