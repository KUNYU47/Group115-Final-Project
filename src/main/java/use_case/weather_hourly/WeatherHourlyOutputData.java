package use_case.weather_hourly;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Output Data for the Weather Use Case.
 */
public class WeatherHourlyOutputData {
    private final JSONObject hourWeatherData;
    private final String city;

    public WeatherHourlyOutputData(JSONArray weatherData,
                                   int numHour,
                                   String city) {
        this.hourWeatherData = weatherData.getJSONObject(numHour);
        this.city = city;
    }

    public JSONObject getHourWeatherData() {
        return hourWeatherData;
    }

    public double getTemperature() {
        return hourWeatherData.getDouble("temp");
    }

    public String getCity() {
        return this.city;
    }

    public String getCondition() {
        return getWeather(this.hourWeatherData.getJSONArray("weather").getJSONObject(0));
    }

    private String getWeather(JSONObject weather) {
        return weather.getString("main");
    }
}
