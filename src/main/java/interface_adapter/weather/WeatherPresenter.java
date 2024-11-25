package interface_adapter.weather;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.weather.WeatherOutputBoundary;
import use_case.weather.WeatherOutputData;

/**
 * The Presenter for the Weather Use Case.
 */
public class WeatherPresenter implements WeatherOutputBoundary {

    private final WeatherViewModel weatherViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public WeatherPresenter(ViewManagerModel viewManagerModel,
                            WeatherViewModel weatherViewModel,
                            LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.weatherViewModel = weatherViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(WeatherOutputData outputData) {
        // On success, update the WeatherViewModel with the received weather data.
        final WeatherState weatherState = weatherViewModel.getState();
        weatherState.setCity(outputData.getCity());
        weatherState.setTemperature(outputData.getTemperature());
        weatherState.setCondition(outputData.getCondition());
        weatherState.setErrorMessage(null);
        weatherViewModel.setState(weatherState);

        weatherViewModel.firePropertyChanged();

        viewManagerModel.setState(weatherViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        // On failure, update the WeatherViewModel with an error message.
        final WeatherState weatherState = weatherViewModel.getState();
        weatherState.setErrorMessage(error);
        weatherViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHourlyView() {
        viewManagerModel.setState();
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToDailyView() {
        viewManagerModel.setState();
        viewManagerModel.firePropertyChanged();
    }

}
