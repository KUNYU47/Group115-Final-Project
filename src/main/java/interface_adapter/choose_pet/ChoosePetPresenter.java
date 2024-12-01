package interface_adapter.choose_pet;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.weather.WeatherViewModel;
import use_case.choose_pet.ChoosePetOutputBoundary;
import use_case.choose_pet.ChoosePetOutputData;

/**
 * The Presenter for the Choose Pet Use Case.
 */
public class ChoosePetPresenter implements ChoosePetOutputBoundary {
    private final ChoosePetViewModel petViewModel;
    private final WeatherViewModel weatherViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public ChoosePetPresenter(ViewManagerModel viewManagerModel,
                              ChoosePetViewModel petViewModel,
                              WeatherViewModel weatherViewModel,
                              LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.petViewModel = petViewModel;
        this.weatherViewModel = weatherViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(ChoosePetOutputData outputData) {
        // Get the current state of the pet view model
        final ChoosePetState petState = petViewModel.getState();

        // Update the state with the selected pet
        petState.setSelectedPet(outputData.getSelectedPet());

        // Clear any error messages
        petState.setErrorMessage(null);

        // Update the view model's state
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
        final ChoosePetState petState = petViewModel.getState();

        // Set the error message
        petState.setErrorMessage(errorMessage);
        petViewModel.setState(petState);

        // Notify that the view model has changed
        petViewModel.firePropertyChanged();
    }

    @Override
    public void switchToCurrView() {
        // Update the view manager to switch to the current view of the pet view model
        viewManagerModel.setState(weatherViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
