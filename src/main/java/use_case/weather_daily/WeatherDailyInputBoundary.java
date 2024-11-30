package use_case.weather_daily;

/**
 * Input boundary for actions which are related with to weather hourly.
 */
public interface WeatherDailyInputBoundary {

    /**
     * Execute the weather use case.
     * @param weatherDailyInputData the input data
     */
    void execute(WeatherDailyInputData weatherDailyInputData);

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
    void switchToHourlyView();
}
