package interface_adapter.weather_daily;

/**
 * The State information representing the weather information.
 */
public class WeatherDailyState {
    private String city;
    private double temperature;
    private String condition;
    private String summary;

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

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getSummary() {
        return summary;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}