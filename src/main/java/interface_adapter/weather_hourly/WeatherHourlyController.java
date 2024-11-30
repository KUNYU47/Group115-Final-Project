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
     * @param hour the hour ahead that we want to forecast.
     */
    public void execute(String city, String hour) {
        final WeatherHourlyInputData weatherInputData = new WeatherHourlyInputData(city, hour);

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
