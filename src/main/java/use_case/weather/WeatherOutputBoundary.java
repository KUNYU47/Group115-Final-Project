package use_case.weather;

/**
 * The output boundary for the Weather Use Case.
 */
public interface WeatherOutputBoundary {
    /**
     * Prepares the success view for the Weather Use Case.
     * @param response the output data
     */
    void prepareSuccessView(WeatherOutputData response);

    /**
     * Prepares the failure view for the Weather Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Logged in View.
     */
    void switchToLoggedInView();
}
