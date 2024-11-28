package interface_adapter.choose_pet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.ViewModel;

/**
 * The View Model for the Choose Pet View.
 */
public class ChoosePetViewModel extends ViewModel<ChoosePetState> {

    public static final String TITLE_LABEL = "Choose Pet Screen";
    public static final String PET_LABEL = "Select Your Pet";
    public static final String CONFIRM_BUTTON_LABEL = "Confirm Selection";
    public static final String ERROR_LABEL = "Error: ";

    private ChoosePetState state = new ChoosePetState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ChoosePetViewModel() {
        super("Choose Your Pet");
    }

    @Override
    public void setState(ChoosePetState state) {
        final ChoosePetState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    @Override
    public ChoosePetState getState() {
        return state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

