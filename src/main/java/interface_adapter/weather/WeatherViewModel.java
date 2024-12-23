package interface_adapter.weather;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.ViewModel;

/**
 * The View Model for the Weather View.
 */
public class WeatherViewModel extends ViewModel<WeatherState> {

    public static final String TITLE_LABEL = "Weather Screen";
    public static final String CITY_LABEL = "City Name";
    public static final String GET_WEATHER_BUTTON_LABEL = "Get Weather";
    public static final String TEMPERATURE_LABEL = "Temperature: ";
    public static final String DESCRIPTION_LABEL = "Description: ";
    public static final String CITY_INFO_LABEL = "City: ";
    public static final String SETTINGS_LABEL = "Settings";
    public static final String CURRENT = "Current";
    public static final String HOURLY = "Hourly";
    public static final String DAILY = "Daily";

    private WeatherState state = new WeatherState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public WeatherViewModel() {
        super("weather");
    }

    @Override
    public void setState(WeatherState state) {
        final WeatherState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    @Override
    public WeatherState getState() {
        return this.state;
    }

    /**
     * Set the pet type in WeatherState when switching view.
     * @param petType the pet type that user picked.
     */
    public void setPetType(String petType) {
        final WeatherState currState = getState();
        currState.setPetType(petType);
        setState(currState);
        firePropertyChanged();
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
