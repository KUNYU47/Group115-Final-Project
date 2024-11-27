package use_case.weather_daily;

public interface WeatherDailyOutputBoundary {

    /**
     * Prepares the success view for the Weather Use Case.
     * @param response the output data
     */
    void prepareSuccessView(WeatherDailyOutputData response);

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
    void switchToHourlyView();
}
