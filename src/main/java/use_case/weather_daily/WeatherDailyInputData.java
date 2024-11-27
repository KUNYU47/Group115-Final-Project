package use_case.weather_daily;

/**
 * The input data for the daily weather use case.
 */
public class WeatherDailyInputData {

    private final String city;
    private final String day;

    public WeatherDailyInputData(String city, String day) {
        this.city = city;
        this.day = day;
    }

    public String getCity() {
        return city;
    }

    public String getDay() {
        return day;
    }

}
