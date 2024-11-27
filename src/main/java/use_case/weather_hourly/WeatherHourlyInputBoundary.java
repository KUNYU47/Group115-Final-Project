package use_case.weather_hourly;

/**
 * Input boundary for actions which are related with to weather.
 */
public interface WeatherHourlyInputBoundary {

    /**
     * Execute the weather use case.
     * @param weatherHourlyInputData the input data
     */
    void execute(WeatherHourlyInputData weatherHourlyInputData);

    /**
     * Executes the switch to logged in view use case.
     */
    void switchToLoggedInView();

    /**
     * Executes the switch to hourly forecast view use case.
     */
    void switchToCurrView();

    /**
     * Executes the switch to daily forecast view use case.
     */
    void switchToDailyView();
}
