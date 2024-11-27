package interface_adapter.weather_hourly;

import use_case.weather_hourly.WeatherHourlyInputBoundary;
import use_case.weather_hourly.WeatherHourlyInputData;

public class WeatherHourlyController {
    private final WeatherHourlyInputBoundary weatherUseCaseInteractor;

    public WeatherHourlyController(WeatherHourlyInputBoundary userWeatherUseCaseInteractor) {
        this.weatherUseCaseInteractor = userWeatherUseCaseInteractor;
    }

    /**
     * Executes the Weather Use Case.
     * @param city the city we are referring.
     */
    public void execute(String city) {
        final WeatherHourlyInputData weatherInputData = new WeatherHourlyInputData(city);

        weatherUseCaseInteractor.execute(weatherInputData);
    }

    /**
     * Executes the "switch to Logged In View" Use Case.
     */
    public void switchToLoggedInView() {
        weatherUseCaseInteractor.switchToLoggedInView();
    }

    /**
     * Executes the switch to hourly forecast view use case.
     */
    public void switchToCurrView() {
        weatherUseCaseInteractor.switchToCurrView();
    }

    /**
     * Executes the switch to daily forecast view use case.
     */
    public void switchToDailyView() {
        weatherUseCaseInteractor.switchToDailyView();
    }
}
