package use_case.weather;

/**
 * Input boundary for actions which are related with to weather.
 */
public interface WeatherInputBoundary {

    /**
     * Execute the weather use case.
     * @param weatherInputData the input data
     */
    void execute(WeatherInputData weatherInputData);

    /**
     * Executes the switch to logged in view use case.
     */
    void switchToLoggedInView();

    /**
     * Executes the switch to hourly forecast view use case.
     */
    void switchToHourlyView();

    /**
     * Executes the switch to daily forecast view use case.
     */
    void switchToDailyView();
}
