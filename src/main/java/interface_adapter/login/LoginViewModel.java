package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {

    public static final int WINDOWITH = 400;

    public LoginViewModel() {
        super("log in");
        setState(new LoginState());
    }

}
