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
}
