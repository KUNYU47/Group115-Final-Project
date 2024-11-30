package use_case.weather;

import org.json.JSONObject;

/**
 * Output Data for the Weather Use Case.
 */
public class WeatherOutputData {
    private final JSONObject currWeatherData;
    private final String city;

    public WeatherOutputData(JSONObject weatherData,
                             String city) {
        this.currWeatherData = weatherData;
        this.city = city;
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
        return getWeather(this.currWeatherData.getJSONArray("weather").getJSONObject(0));
    }

    public String getDescription() {
        return getDesHelper(this.currWeatherData.getJSONArray("weather").getJSONObject(0));
    }

    private String getWeather(JSONObject weather) {
        return weather.getString("main");
    }

    private String getDesHelper(JSONObject weather) {
        return weather.getString("description");
    }
}
