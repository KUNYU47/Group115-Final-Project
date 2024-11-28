package use_case.choose_pet;

import java.util.Arrays;
import java.util.List;

/**
 * Interactor for the Choose Pet use case.
 * This class implements the use case logic for selecting a pet.
 */
public class ChoosePetInteractor implements ChoosePetInputBoundary {

    private final ChoosePetOutputBoundary choosePetPresenter;

    // A static list of available pets
    private final List<String> AVAILABLE_PETS = Arrays.asList("Dog", "Cat", "Rabbit", "Fox");

    /**
     * Constructor for ChoosePetInteractor.
     * @param choosePetPresenter The output boundary to send the results to the presenter.
     */
    public ChoosePetInteractor(ChoosePetOutputBoundary choosePetPresenter) {
        this.choosePetPresenter = choosePetPresenter;
    }

    @Override
    public void execute(ChoosePetInputData inputData) {
        final String selectedPet = inputData.getSelectedPet();

        // Check if the selected pet is valid (exists in the available pets list)
        if (selectedPet == null || selectedPet.isEmpty() || !AVAILABLE_PETS.contains(selectedPet)) {
            choosePetPresenter.prepareFailView("Invalid pet selection. Please select a valid pet from: " + AVAILABLE_PETS);
        }
        else {
            // If valid, create output data and prepare the success view
            final ChoosePetOutputData outputData = new ChoosePetOutputData(selectedPet, "Pet selection successful!");
            choosePetPresenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToCurrView() {
        choosePetPresenter.switchToCurrView();
    }
}
