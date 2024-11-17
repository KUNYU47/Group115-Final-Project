package use_case.weather;

import use_case.login.LoginOutputData;

public interface WeatherOutputBoundary {
    /**
     * Prepares the success view for the Weather Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LoginOutputData outputData);

    /**
     * Prepares the failure view for the Weather Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
