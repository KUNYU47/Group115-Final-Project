package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_pet.ChoosePetViewModel;
import interface_adapter.weather.WeatherViewModel;
import interface_adapter.weather_daily.WeatherDailyViewModel;
import interface_adapter.weather_hourly.WeatherHourlyViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final WeatherViewModel weatherViewModel;
    private final WeatherHourlyViewModel weatherHourlyViewModel;
    private final WeatherDailyViewModel weatherDailyViewModel;
    private final ChoosePetViewModel choosePetViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public ChangePasswordPresenter(WeatherViewModel weatherViewModel,
                                   WeatherHourlyViewModel weatherHourlyViewModel,
                                   WeatherDailyViewModel weatherDailyViewModel,
                                   ChoosePetViewModel choosePetViewModel,
                                   LoggedInViewModel loggedInViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.weatherViewModel = weatherViewModel;
        this.weatherHourlyViewModel = weatherHourlyViewModel;
        this.weatherDailyViewModel = weatherDailyViewModel;
        this.choosePetViewModel = choosePetViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the username, which remains the same.
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully..
        loggedInViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
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

    @Override
    public void switchToDailyView() {
        viewManagerModel.setState(weatherDailyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToChoosePetView() {
        viewManagerModel.setState(choosePetViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
