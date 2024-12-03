package interface_adapter.weather;

/**
 * The State information representing the weather information.
 */
public class WeatherState {
    private String city;
    private double temperature;
    private String condition;
    private String description;
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

    public String getPetType() {
        return petType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
