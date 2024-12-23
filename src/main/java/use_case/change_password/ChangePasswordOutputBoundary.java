package use_case.change_password;

/**
 * The output boundary for the Change Password Use Case.
 */
public interface ChangePasswordOutputBoundary {
    /**
     * Prepares the success view for the Change Password Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ChangePasswordOutputData outputData);

    /**
     * Prepares the failure view for the Change Password Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch to Weather View.
     */
    void switchToCurrView();

    /**
     * Switch to hourly forecast view.
     */
    void switchToHourlyView();

    /**
     * Switch to daily forecast view.
     */
    void switchToDailyView();

    /**
     * Switch to choose pet view.
     */
    void switchToChoosePetView();
}
