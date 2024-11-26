package use_case.change_password;

/**
 * The Change Password Use Case.
 */
public interface ChangePasswordInputBoundary {

    /**
     * Execute the Change Password Use Case.
     * @param changePasswordInputData the input data for this use case
     */
    void execute(ChangePasswordInputData changePasswordInputData);

    /**
     * Executes the "switch to Logged In View" Use Case.
     */
    void switchToCurrView();

    /**
     * Executes the switch to hourly forecast view use case.
     */
    void switchToHourlyView();

    /**
     * Executes the switch to daily forecast view use case.
     */
    void switchToDailyView();

}
