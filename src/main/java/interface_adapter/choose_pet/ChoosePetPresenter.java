import interface_adapter.ViewManagerModel;
import interface_adapter.choose_pet.ChoosePetState;
import interface_adapter.choose_pet.ChoosePetViewModel;
import use_case.choose_pet.ChoosePetOutputData;

public class ChoosePetPresenter {
    private final ChoosePetViewModel petViewModel;
    private final ViewManagerModel viewManagerModel;

    public ChoosePetPresenter(ViewManagerModel viewManagerModel, ChoosePetViewModel petViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.petViewModel = petViewModel;
    }

    @Override
    public void prepareSuccessView(ChoosePetOutputData outputData) {
        // Get the current state of the pet view model
        ChoosePetState petState = petViewModel.getState();

        // Update the state with success information (clear any error messages)
        petState.setErrorMessage(null);
        petViewModel.setState(petState);

        // Notify that the view model has changed
        petViewModel.firePropertyChanged();

        // Update the view manager model with the correct view name
        viewManagerModel.setState(petViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Get the current state of the pet view model
        ChoosePetState petState = petViewModel.getState();

        // Set the error message
        petState.setErrorMessage(errorMessage);
        petViewModel.setState(petState);

        // Notify that the view model has changed
        petViewModel.firePropertyChanged();
    }

    @Override
    public void switchToCurrView() {
        // Update the view manager to switch to the current view of the pet view model
        viewManagerModel.setState(petViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}