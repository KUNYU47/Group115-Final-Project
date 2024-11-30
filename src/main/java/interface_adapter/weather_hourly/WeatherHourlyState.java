package interface_adapter.weather_hourly;

/**
 * The State information representing the weather hourly information.
 */
public class WeatherHourlyState {
    private String city;
    private double temperature;
    private String condition;

    private String errorMessage;

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(double temp) {
        this.temperature = temp;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return String.valueOf(temperature);
    }

    public String getCondition() {
        return condition;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
