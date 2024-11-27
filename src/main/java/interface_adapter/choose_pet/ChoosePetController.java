import use_case.choose_pet.ChoosePetInputBoundary;
import use_case.choose_pet.ChoosePetInputData;

public class ChoosePetController {
    private final ChoosePetInputBoundary PetUseCaseInteractor;

    public ChoosePetController(ChoosePetInputBoundary userPetUseCaseInteractor) {
        this.PetUseCaseInteractor = userPetUseCaseInteractor;
    }

    /**
     * Executes the Weather Use Case.
     * @param city the city we are referring.
     */
    public void execute(String city) {
        final ChoosePetInputData PetInputData = new ChoosePetInputData(city);

        PetUseCaseInteractor.execute(PetInputData);
    }

    /**
     * Executes the "switch to Logged In View" Use Case.
     */
    public void switchToLoggedInView() {
        PetUseCaseInteractor.switchToLoggedInView();
    }

    /**
     * Executes the switch to hourly forecast view use case.
     */
    public void switchToCurrView() {
        PetUseCaseInteractor.switchToCurrView();
    }
}
