package data_access;

import org.json.JSONObject;

public interface WeatherDataAccessInterface {

    /**
     * Fetches the coordinates for a given city name.
     *
     * @param cityName The name of the city.
     * @return A JSONObject containing latitude and longitude or null if not found.
     */
    JSONObject getCoordinates(String cityName);

    /**
     * Fetches the weather data for a given latitude and longitude.
     *
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return A JSONObject containing weather data or null if not found.
     */
    JSONObject getWeatherData(double lat, double lon);
}
