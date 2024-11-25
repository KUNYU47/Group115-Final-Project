package interface_adapter.weatherminute;

import use_case.weatherminute.WeatherMinuteInputBoundary;
import use_case.weatherminute.WeatherMinuteInputData;

public class WeatherMinuteController {
    private final WeatherMinuteInputBoundary weatherUseCaseInteractor;

    public WeatherMinuteController(WeatherMinuteInputBoundary weatherUseCaseInteractor) {
        this.weatherUseCaseInteractor = weatherUseCaseInteractor;
    }

    /**
     * Executes the Weather Use Case.
     * @param city the city we are referring.
     */
    public void execute(String city) {
        final WeatherMinuteInputData weatherminuteInputData = new WeatherMinuteInputData(city);

        weatherUseCaseInteractor.execute(weatherminuteInputData);
    }

    /**
     * Executes the "switch to Logged In View" Use Case.
     */
    public void switchToLoggedInView() {
        weatherUseCaseInteractor.switchToLoggedInView();
    }
}
