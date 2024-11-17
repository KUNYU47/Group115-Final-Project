package use_case.weather;

/**
 * The input data for the weather use case.
 */
public class WeatherInputData {

    private final String city;

    public WeatherInputData(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

}
