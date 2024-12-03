package interface_adapter.choose_pet;

import use_case.choose_pet.ChoosePetInputBoundary;
import use_case.choose_pet.ChoosePetInputData;

/**
 * Controller for the Choose Pet Use Case.
 */
public class ChoosePetController {
    private final ChoosePetInputBoundary petUseCaseInteractor;

    public ChoosePetController(ChoosePetInputBoundary userPetUseCaseInteractor) {
        this.petUseCaseInteractor = userPetUseCaseInteractor;
    }

    /**
     * Executes the pet Use Case.
     * @param pet the pet we are referring.
     */
    public void execute(String pet) {
        final ChoosePetInputData petInputData = new ChoosePetInputData(pet);

        petUseCaseInteractor.execute(petInputData);
    }

    /**
     * Executes the switch to current weather view use case.
     */
    public void switchToCurrView() {
        petUseCaseInteractor.switchToCurrView();
    }

    /**
     * Executes the switch to hourly forecast view use case.
     */
    public void switchToHourlyView() {
        petUseCaseInteractor.switchToHourlyView();
    }

    /**
     * Executes the switch to daily forecast view use case.
     */
    public void switchToDailyView() {
        petUseCaseInteractor.switchToDailyView();
    }
}
