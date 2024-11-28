package use_case.choose_pet;

/**
 * Output Boundary for the Choose Pet use case.
 * This interface defines the contract between the interactor and the presenter for outputting the result.
 */
public interface ChoosePetOutputBoundary {

    /**
     * Prepares the success view with the output data.
     * @param outputData The output data from executing the choose pet use case.
     */
    void prepareSuccessView(ChoosePetOutputData outputData);

    /**
     * Prepares the fail view with an error message.
     * @param errorMessage The error message to be displayed.
     */
    void prepareFailView(String errorMessage);
}
