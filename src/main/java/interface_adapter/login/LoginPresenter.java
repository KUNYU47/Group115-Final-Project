package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.choose_pet.ChoosePetState;
import interface_adapter.choose_pet.ChoosePetViewModel;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final WeatherViewModel weatherViewModel;
    private final ChoosePetViewModel choosePetViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          WeatherViewModel weatherViewModel,
                          ChoosePetViewModel choosePetViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.weatherViewModel = weatherViewModel;
        this.choosePetViewModel = choosePetViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {

//        final WeatherState weatherState = weatherViewModel.getState();
//        this.weatherViewModel.setState(weatherState);
//        this.weatherViewModel.firePropertyChanged();
//
//        this.viewManagerModel.setState(weatherViewModel.getViewName());

        final ChoosePetState choosePetState = choosePetViewModel.getState();
        this.choosePetViewModel.setState(choosePetState);
        this.choosePetViewModel.firePropertyChanged();

        this.viewManagerModel.setState(choosePetViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
