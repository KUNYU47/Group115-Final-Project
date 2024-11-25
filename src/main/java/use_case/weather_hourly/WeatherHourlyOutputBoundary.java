package use_case.weather_hourly;

/**
 * The output boundary for the Weather Use Case.
 */
public interface WeatherHourlyOutputBoundary {
    /**
     * Prepares the success view for the Weather Use Case.
     * @param response the output data
     */
    void prepareSuccessView(WeatherHourlyOutputData response);

    /**
     * Prepares the failure view for the Weather Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Logged in View.
     */
    void switchToLoggedInView();

    /**
     * Switches to the hourly forecast view use case.
     */
    void switchToCurrView();

    /**
     * Switches to the daily forecast view use case.
     */
    void switchToDailyView();
}
