package interface_adapter.weather_hourly;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.ViewModel;

/**
 * The View Model for the Weather Hourly View.
 */
public class WeatherHourlyViewModel extends ViewModel<WeatherHourlyState> {

    public static final String TITLE_LABEL = "Weather Hourly Screen";
    public static final String CITY_LABEL = "City Name";
    public static final String HOUR_LABEL = "Hours ahead (0 ~ 47)";
    public static final String GET_WEATHER_BUTTON_LABEL = "Get Weather";
    public static final String TEMPERATURE_LABEL = "Temperature: ";
    public static final String DESCRIPTION_LABEL = "Description: ";
    public static final String CITY_INFO_LABEL = "City: ";
    public static final String SETTINGS_LABEL = "Settings";
    public static final String CURRENT = "Current";
    public static final String HOURLY = "Hourly";
    public static final String DAILY = "Daily";

    private WeatherHourlyState state = new WeatherHourlyState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public WeatherHourlyViewModel() {
        super("weather hourly");
    }

    @Override
    public void setState(WeatherHourlyState state) {
        final WeatherHourlyState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    public WeatherHourlyState getState() {
        return this.state;
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
