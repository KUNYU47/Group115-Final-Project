package interface_adapter.weather_daily;

/**
 * The State information representing the weather daily information.
 */
public class WeatherDailyState {
    private String city;
    private double temperature;
    private String condition;
    private String description;
    private String summary;
    private String petType;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPetType(String petType) {
        this.petType = petType;
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

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

    public String getPetType() {
        return petType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
