package use_case.weather;

import org.json.JSONObject;

public class WeatherOutputData {
    private final JSONObject weatherData;
    private final boolean useCaseFailed;

    public WeatherOutputData(JSONObject weatherData, boolean useCaseFailed) {
        this.weatherData = weatherData;
        this.useCaseFailed = useCaseFailed;
    }

    public JSONObject getWeatherData() {
        return weatherData;
    }
}
