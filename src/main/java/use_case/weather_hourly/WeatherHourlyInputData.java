package use_case.weather_hourly;

/**
 * The input data for the hourly weather use case.
 */
public class WeatherHourlyInputData {

    private final String city;

    public WeatherHourlyInputData(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

}
