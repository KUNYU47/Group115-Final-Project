package interface_adapter.weather;

import use_case.weather.WeatherInputBoundary;
import use_case.weather.WeatherInputData;

/**
 * Controller for the Signup Use Case.
 */
public class WeatherController {

    private final WeatherInputBoundary weatherUseCaseInteractor;

    public WeatherController(WeatherInputBoundary userWeatherUseCaseInteractor) {
        this.weatherUseCaseInteractor = userWeatherUseCaseInteractor;
    }

    /**
     * Executes the Weather Use Case.
     * @param city the city we are referring.
     */
    public void execute(String city) {
        final WeatherInputData weatherInputData = new WeatherInputData(city);

        weatherUseCaseInteractor.execute(weatherInputData);
    }

    /**
     * Executes the "switch to Logged In View" Use Case.
     */
    public void switchToLoggedInView() {
        weatherUseCaseInteractor.switchToLoggedInView();
    }
}

