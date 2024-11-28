package use_case.choose_pet;

/**
 * Input Boundary for the Choose Pet use case.
 * This interface defines the contract between the controller and the interactor for selecting a pet.
 */
public interface ChoosePetInputBoundary {

    /**
     * Executes the choose pet use case.
     * @param inputData The input data required to perform the pet selection.
     */
    void execute(ChoosePetInputData inputData);

    /**
     * Switch to the current view after choosing a pet.
     */
    void switchToCurrView();
}
