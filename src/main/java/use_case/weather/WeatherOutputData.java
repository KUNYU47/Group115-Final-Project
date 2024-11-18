package use_case.weather;

import org.json.JSONObject;

/**
 * Output Data for the Weather Use Case.
 */
public class WeatherOutputData {
    private final JSONObject currWeatherData;
    private final String city;
    private final boolean useCaseFailed;

    public WeatherOutputData(JSONObject weatherData,
                             String city,
                             boolean useCaseFailed) {
        this.currWeatherData = weatherData;
        this.city = city;
        this.useCaseFailed = useCaseFailed;
    }

    public JSONObject getCurrWeatherData() {
        return currWeatherData;
    }

    public double getTemperature() {
        return currWeatherData.getDouble("temp");
    }

    public String getCity() {
        return this.city;
    }

    public String getCondition() {
        return getWeather(this.currWeatherData.getJSONObject("weather"));
    }

    private String getWeather(JSONObject weather) {
        return weather.getString("main");
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
