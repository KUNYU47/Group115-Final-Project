package use_case.weather_daily;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Output Data for the Weather Use Case.
 */
public class WeatherDailyOutputData {
    private final JSONObject dayWeatherData;
    private final String city;
    private final boolean useCaseFailed;

    public WeatherDailyOutputData(JSONArray weatherData,
                                  int numDay,
                                  String city,
                                  boolean useCaseFailed) {
        this.dayWeatherData = weatherData.getJSONObject(numDay);
        this.city = city;
        this.useCaseFailed = useCaseFailed;
    }

    public JSONObject getDayWeatherData() {
        return dayWeatherData;
    }

    public double getTemperature() {
        return dayWeatherData.getJSONObject("temp").getDouble("day");
    }

    public String getCity() {
        return this.city;
    }

    public String getCondition() {
        return getWeather(this.dayWeatherData.getJSONArray("weather").getJSONObject(0));
    }

    public String getDescription() {
        return getDesHelper(this.dayWeatherData.getJSONArray("weather").getJSONObject(0));
    }

    private String getWeather(JSONObject weather) {
        return weather.getString("main");
    }

    private String getDesHelper(JSONObject weather) {
        return weather.getString("description");
    }

    public String getSummary() {
        return dayWeatherData.getString("summary") + ".";
    }
}
