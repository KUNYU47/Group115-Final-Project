package interface_adapter.weather;

public class WeatherState {
    private String city;
    private double temperature;
    private String condition;
    private String errorMessage;

    public void setCity(Object city) {
        if (city instanceof String) {
            this.city = (String) city;
        }
    }

    public String getCity() {
        return city;
    }

    public void setTemperature(Object temp) {
        if (temp instanceof Number) {
            this.temperature = ((Number) temp).doubleValue();
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public void setCondition(Object condition) {
        if (condition instanceof String) {
            this.condition = (String) condition;
        }
    }

    public String getCondition() {
        return condition;
    }

    public void setErrorMessage(Object errorMessage) {
        if (errorMessage instanceof String) {
            this.errorMessage = (String) errorMessage;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
