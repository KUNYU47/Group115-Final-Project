import use_case.choose_pet.ChoosePetInputBoundary;
import use_case.choose_pet.ChoosePetInputData;

public class ChoosePetController {
    private final ChoosePetInputBoundary PetUseCaseInteractor;

    public ChoosePetController(ChoosePetInputBoundary userPetUseCaseInteractor) {
        this.PetUseCaseInteractor = userPetUseCaseInteractor;
    }

    /**
     * Executes the pet Use Case.
     * @param pet the pet we are referring.
     */
    public void execute(String pet) {
        final ChoosePetInputData PetInputData = new ChoosePetInputData(pet);

        PetUseCaseInteractor.execute(PetInputData);
    }

    /**
     * Executes the switch to hourly forecast view use case.
     */
    public void switchToCurrView() {
        PetUseCaseInteractor.switchToCurrView();
    }
}
