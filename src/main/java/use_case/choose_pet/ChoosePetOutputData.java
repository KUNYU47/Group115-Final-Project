package use_case.choose_pet;

/**
 * Output Data for the Choose Pet use case.
 * This class represents the data produced as a result of executing the choose pet use case.
 */
public class ChoosePetOutputData {
    private final String selectedPet;
    private final String successMessage;

    /**
     * Constructor for ChoosePetOutputData.
     * @param selectedPet The name of the selected pet.
     * @param successMessage A message indicating the success of the operation.
     */
    public ChoosePetOutputData(String selectedPet, String successMessage) {
        this.selectedPet = selectedPet;
        this.successMessage = successMessage;
    }

    /**
     * Gets the name of the selected pet.
     * @return The selected pet.
     */
    public String getSelectedPet() {
        return selectedPet;
    }

    /**
     * Gets the success message.
     * @return The success message.
     */
    public String getSuccessMessage() {
        return successMessage;
    }
}
