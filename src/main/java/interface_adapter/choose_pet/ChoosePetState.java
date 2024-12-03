package interface_adapter.choose_pet;

/**
 * The State information representing the pet.
 */
public class ChoosePetState {
    private String selectedPet;
    private String errorMessage;

    // Constructor
    public ChoosePetState() {
        // Default constructor for initializing an empty state
    }

    // Getters and setters for selected pet
    public String getSelectedPet() {
        return selectedPet;
    }

    public void setSelectedPet(String selectedPet) {
        this.selectedPet = selectedPet;
    }

    // Getters and setters for error message
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
