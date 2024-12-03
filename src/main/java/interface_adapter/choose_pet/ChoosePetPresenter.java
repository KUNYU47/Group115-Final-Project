package interface_adapter.choose_pet;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;
import interface_adapter.weather_daily.WeatherDailyViewModel;
import interface_adapter.weather_hourly.WeatherHourlyViewModel;
import use_case.choose_pet.ChoosePetOutputBoundary;
import use_case.choose_pet.ChoosePetOutputData;

/**
 * The Presenter for the Choose Pet Use Case.
 */
public class ChoosePetPresenter implements ChoosePetOutputBoundary {
    private final ChoosePetViewModel petViewModel;
    private final WeatherViewModel weatherViewModel;
    private final WeatherHourlyViewModel weatherHourlyViewModel;
    private final WeatherDailyViewModel weatherDailyViewModel;
    private final ViewManagerModel viewManagerModel;

    public ChoosePetPresenter(ViewManagerModel viewManagerModel,
                              ChoosePetViewModel petViewModel,
                              WeatherViewModel weatherViewModel,
                              WeatherHourlyViewModel weatherHourlyViewModel,
                              WeatherDailyViewModel weatherDailyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.petViewModel = petViewModel;
        this.weatherViewModel = weatherViewModel;
        this.weatherHourlyViewModel = weatherHourlyViewModel;
        this.weatherDailyViewModel = weatherDailyViewModel;
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
        final ChoosePetState currentState = petViewModel.getState();
        final String petType = currentState.getSelectedPet();
        weatherViewModel.setPetType(petType);
        weatherViewModel.firePropertyChanged();
        // Update the view manager to switch to the current view of the pet view model
        viewManagerModel.setState(weatherViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHourlyView() {
        final ChoosePetState currentState = petViewModel.getState();
        final String petType = currentState.getSelectedPet();
        weatherHourlyViewModel.setPetType(petType);
        weatherHourlyViewModel.firePropertyChanged();
        // Update the view manager to switch to the current view of the pet view model
        viewManagerModel.setState(weatherHourlyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToDailyView() {
        final ChoosePetState currentState = petViewModel.getState();
        final String petType = currentState.getSelectedPet();
        weatherDailyViewModel.setPetType(petType);
        weatherDailyViewModel.firePropertyChanged();
        // Update the view manager to switch to the current view of the pet view model
        viewManagerModel.setState(weatherDailyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
