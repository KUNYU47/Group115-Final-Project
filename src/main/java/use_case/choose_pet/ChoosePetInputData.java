package use_case.choose_pet;

/**
 * Input Data for the Choose Pet use case.
 * This class represents the input required to execute the choose pet use case.
 */
public class ChoosePetInputData {
    private final String selectedPet;

    /**
     * Constructor for ChoosePetInputData.
     * @param selectedPet The name of the selected pet.
     */
    public ChoosePetInputData(String selectedPet) {
        this.selectedPet = selectedPet;
    }

    /**
     * Gets the name of the selected pet.
     * @return The selected pet.
     */
    public String getSelectedPet() {
        return selectedPet;
    }
}
