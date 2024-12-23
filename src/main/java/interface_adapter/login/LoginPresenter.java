package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_pet.ChoosePetState;
import interface_adapter.choose_pet.ChoosePetViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ChoosePetViewModel choosePetViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(SignupViewModel signupViewModel,
                          ViewManagerModel viewManagerModel,
                          ChoosePetViewModel choosePetViewModel,
                          LoginViewModel loginViewModel) {
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.choosePetViewModel = choosePetViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {

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

    @Override
    public void switchToSignUpView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
