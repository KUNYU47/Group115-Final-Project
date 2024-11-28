package use_case.choose_pet;

import java.util.Arrays;
import java.util.List;

/**
 * Interactor for the Choose Pet use case.
 * This class implements the use case logic for selecting a pet.
 */
public class ChoosePetInteractor implements ChoosePetInputBoundary {

    private final ChoosePetOutputBoundary outputBoundary;

    // A static list of available pets
    private static final List<String> AVAILABLE_PETS = Arrays.asList("Dog", "Cat", "Rabbit", "Fox");

    /**
     * Constructor for ChoosePetInteractor.
     * @param outputBoundary The output boundary to send the results to the presenter.
     */
    public ChoosePetInteractor(ChoosePetOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(ChoosePetInputData inputData) {
        String selectedPet = inputData.getSelectedPet();

        // Check if the selected pet is valid (exists in the available pets list)
        if (selectedPet == null || selectedPet.isEmpty() || !AVAILABLE_PETS.contains(selectedPet)) {
            outputBoundary.prepareFailView("Invalid pet selection. Please select a valid pet from: " + AVAILABLE_PETS);
        } else {
            // If valid, create output data and prepare the success view
            ChoosePetOutputData outputData = new ChoosePetOutputData(selectedPet, "Pet selection successful!");
            outputBoundary.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToCurrView() {
        // Logic to switch to the current view, if necessary
        // This could include notifying the output boundary to update the view
        outputBoundary.prepareSuccessView(new ChoosePetOutputData(null, "Switching to current view."));
    }
}
