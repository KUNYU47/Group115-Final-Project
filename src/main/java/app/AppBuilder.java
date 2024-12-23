package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryUserDataAccessObject;
import data_access.WeatherDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.choose_pet.ChoosePetController;
import interface_adapter.choose_pet.ChoosePetPresenter;
import interface_adapter.choose_pet.ChoosePetViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherPresenter;
import interface_adapter.weather.WeatherViewModel;
import interface_adapter.weather_daily.WeatherDailyController;
import interface_adapter.weather_daily.WeatherDailyPresenter;
import interface_adapter.weather_daily.WeatherDailyViewModel;
import interface_adapter.weather_hourly.WeatherHourlyController;
import interface_adapter.weather_hourly.WeatherHourlyPresenter;
import interface_adapter.weather_hourly.WeatherHourlyViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.choose_pet.ChoosePetInputBoundary;
import use_case.choose_pet.ChoosePetInteractor;
import use_case.choose_pet.ChoosePetOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.weather.WeatherDataAccessInterface;
import use_case.weather.WeatherInputBoundary;
import use_case.weather.WeatherInteractor;
import use_case.weather.WeatherOutputBoundary;
import use_case.weather_daily.WeatherDailyDataAccessInterface;
import use_case.weather_daily.WeatherDailyInputBoundary;
import use_case.weather_daily.WeatherDailyInteractor;
import use_case.weather_daily.WeatherDailyOutputBoundary;
import use_case.weather_hourly.WeatherHourlyDataAccessInterface;
import use_case.weather_hourly.WeatherHourlyInputBoundary;
import use_case.weather_hourly.WeatherHourlyInteractor;
import use_case.weather_hourly.WeatherHourlyOutputBoundary;
import view.ChoosePetView;
import view.LoggedInView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;
import view.WeatherDailyView;
import view.WeatherHourlyView;
import view.WeatherView;
/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.

@SuppressWarnings({"checkstyle:ClassDataAbstractionCoupling",
                   "checkstyle:ClassFanOutComplexity",
                   "checkstyle:SuppressWarnings"})

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginView loginView;
    private LoginViewModel loginViewModel;
    private LoggedInView loggedInView;
    private LoggedInViewModel loggedInViewModel;
    private WeatherView weatherView;
    private WeatherViewModel weatherViewModel;
    private WeatherHourlyView weatherHourlyView;
    private WeatherHourlyViewModel weatherHourlyViewModel;
    private WeatherDailyView weatherDailyView;
    private WeatherDailyViewModel weatherDailyViewModel;
    private ChoosePetView choosePetView;
    private ChoosePetViewModel choosePetViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the Weather View to the application.
     * @return this builder.
     */
    public AppBuilder addWeatherView() {
        weatherViewModel = new WeatherViewModel();
        weatherView = new WeatherView(weatherViewModel);
        cardPanel.add(weatherView, weatherView.getViewName());
        return this;
    }

    /**
     * Adds the Weather Hourly View to the application.
     * @return this builder.
     */
    public AppBuilder addWeatherHourlyView() {
        weatherHourlyViewModel = new WeatherHourlyViewModel();
        weatherHourlyView = new WeatherHourlyView(weatherHourlyViewModel);
        cardPanel.add(weatherHourlyView, weatherHourlyView.getViewName());
        return this;
    }

    /**
     * Adds the Weather Daily View to the application.
     * @return this builder.
     */
    public AppBuilder addWeatherDailyView() {
        weatherDailyViewModel = new WeatherDailyViewModel();
        weatherDailyView = new WeatherDailyView(weatherDailyViewModel);
        cardPanel.add(weatherDailyView, weatherDailyView.getViewName());
        return this;
    }

    /**
     * Adds the Choose Pet View to the application.
     * @return this builder.
     */
    public AppBuilder addChoosePetView() {
        choosePetViewModel = new ChoosePetViewModel();
        choosePetView = new ChoosePetView(choosePetViewModel);
        cardPanel.add(choosePetView, choosePetView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(signupViewModel,
                                                                           viewManagerModel,
                                                                           choosePetViewModel,
                                                                           loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(weatherViewModel,
                                            weatherHourlyViewModel,
                                            weatherDailyViewModel,
                                            choosePetViewModel,
                                            loggedInViewModel,
                                            viewManagerModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Weather Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWeatherUseCase() {
        final WeatherOutputBoundary weatherOutputBoundary =
                new WeatherPresenter(viewManagerModel,
                                     weatherViewModel,
                                     weatherHourlyViewModel,
                                     weatherDailyViewModel,
                                     loggedInViewModel);

        final WeatherDataAccessInterface weatherDataAccessObject = new WeatherDataAccessObject();

        final WeatherInputBoundary weatherInteractor =
                new WeatherInteractor(weatherDataAccessObject, weatherOutputBoundary);

        final WeatherController weatherController = new WeatherController(weatherInteractor);
        weatherView.setWeatherController(weatherController);
        return this;
    }

    /**
     * Adds the Weather Houely Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWeatherHourlyUseCase() {
        final WeatherHourlyOutputBoundary weatherHourlyOutputBoundary =
                new WeatherHourlyPresenter(viewManagerModel,
                                           weatherViewModel,
                                           weatherHourlyViewModel,
                                           weatherDailyViewModel,
                                           loggedInViewModel);

        final WeatherHourlyDataAccessInterface weatherHourlyDataAccessObject = new WeatherDataAccessObject();

        final WeatherHourlyInputBoundary weatherHourlyInteractor =
                new WeatherHourlyInteractor(weatherHourlyDataAccessObject, weatherHourlyOutputBoundary);

        final WeatherHourlyController weatherHourlyController = new WeatherHourlyController(weatherHourlyInteractor);
        weatherHourlyView.setWeatherController(weatherHourlyController);
        return this;
    }

    /**
     * Adds the Weather Daily Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWeatherDailyUseCase() {
        final WeatherDailyOutputBoundary weatherDailyOutputBoundary =
                new WeatherDailyPresenter(viewManagerModel,
                                          weatherViewModel,
                                          weatherHourlyViewModel,
                                          weatherDailyViewModel,
                                          loggedInViewModel);

        final WeatherDailyDataAccessInterface weatherDailyDataAccessObject = new WeatherDataAccessObject();

        final WeatherDailyInputBoundary weatherDailyInteractor =
                new WeatherDailyInteractor(weatherDailyDataAccessObject, weatherDailyOutputBoundary);

        final WeatherDailyController weatherDailyController = new WeatherDailyController(weatherDailyInteractor);
        weatherDailyView.setWeatherDailyController(weatherDailyController);
        return this;
    }

    /**
     * Adds the Choose Pet Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChoosePetUseCase() {
        final ChoosePetOutputBoundary choosePetOutputBoundary =
                new ChoosePetPresenter(viewManagerModel, choosePetViewModel, weatherViewModel,
                        weatherHourlyViewModel, weatherDailyViewModel);

        final ChoosePetInputBoundary choosePetInteractor = new ChoosePetInteractor(choosePetOutputBoundary);

        final ChoosePetController choosePetController = new ChoosePetController(choosePetInteractor);
        choosePetView.setChoosePetController(choosePetController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Weather App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
