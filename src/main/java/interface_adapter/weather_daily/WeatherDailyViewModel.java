package interface_adapter.weather_daily;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.ViewModel;

/**
 * The View Model for the Weather Daily View.
 */
public class WeatherDailyViewModel extends ViewModel<WeatherDailyState> {

    public static final String TITLE_LABEL = "Weather Daily Screen";
    public static final String CITY_LABEL = "City Name";
    public static final String DAY_LABEL = "Days ahead (0 ~ 7)";
    public static final String GET_WEATHER_BUTTON_LABEL = "Get Weather";
    public static final String TEMPERATURE_LABEL = "Temperature: ";
    public static final String CONDITION_LABEL = "Condition: ";
    public static final String DESCRIPTION_LABEL = "Description: ";
    public static final String CITY_INFO_LABEL = "City: ";
    public static final String SUMMARY_LABEL = "Summary: ";
    public static final String SETTINGS_LABEL = "Settings";
    public static final String CURRENT = "Current";
    public static final String HOURLY = "Hourly";
    public static final String DAILY = "Daily";
    private static final String[] MODE_OPTIONS = new String[] {CURRENT, HOURLY, DAILY};

    private WeatherDailyState state = new WeatherDailyState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public WeatherDailyViewModel() {
        super("weather daily");
    }

    @Override
    public void setState(WeatherDailyState state) {
        final WeatherDailyState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, state);
    }

    public WeatherDailyState getState() {
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

    public String[] getModeOptions() {
        return MODE_OPTIONS;
    }
}
