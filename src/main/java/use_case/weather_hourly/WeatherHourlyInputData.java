package use_case.weather_hourly;

/**
 * The input data for the hourly weather use case.
 */
public class WeatherHourlyInputData {

    private final String city;
    private final String hour;

    public WeatherHourlyInputData(String city, String hour) {
        this.city = city;
        this.hour = hour;
    }

    public String getCity() {
        return city;
    }

    public String getHour() {
        return hour;
    }

}
