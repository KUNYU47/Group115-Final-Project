package interface_adapter.weather;

import use_case.weather.WeatherInputBoundary;
import use_case.weather.WeatherInputData;

/**
 * Controller for the Signup Use Case.
 */
public class WeatherController {

    private final WeatherInputBoundary userWeatherUseCaseInteractor;

    public WeatherController(WeatherInputBoundary userWeatherUseCaseInteractor) {
        this.userWeatherUseCaseInteractor = userWeatherUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param city the city we are referring.
     */
    public void execute(String city) {
        final WeatherInputData WeatherInputData = new WeatherInputData(
                city);

        userWeatherUseCaseInteractor.execute(WeatherInputData);
    }
}
