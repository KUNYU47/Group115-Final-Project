package interface_adapter.weather_daily;

import use_case.weather_daily.WeatherDailyInputBoundary;
import use_case.weather_daily.WeatherDailyInputData;

public class WeatherDailyController {
    private final WeatherDailyInputBoundary weatherUseCaseInteractor;

    public WeatherDailyController(WeatherDailyInputBoundary weatherUseCaseInteractor) {
        this.weatherUseCaseInteractor = weatherUseCaseInteractor;
    }

    /**
     * Executes the Weather Daily Use Case.
     * @param city the city we are referring.
     * @param day the number of days ahead that we want to forecast.
     */
    public void execute(String city, String day) {
        final WeatherDailyInputData weatherInputData = new WeatherDailyInputData(city, day);

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
    public void switchToHourlyView() {
        weatherUseCaseInteractor.switchToHourlyView();
    }
}
