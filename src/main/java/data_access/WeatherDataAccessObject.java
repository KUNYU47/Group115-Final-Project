package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import use_case.weather.WeatherDataAccessInterface;

public class WeatherDataAccessObject implements WeatherDataAccessInterface {
    private static final String API_KEY;

    static {
        API_KEY = "d918d0fcce1238fa1bc59c035b57621c";
    }

    /**
     * Method for fetching weather data using One Call API 3.0
     * @param latitude the latitude of the target location
     * @param longitude the longitude of the target location
     * @return Return the Weather Data from the api
     */
    public JSONObject getWeatherData(double latitude, double longitude) {
        final String urlString = String.format(
                "https://api.openweathermap.org/data/3.0/onecall?lat=%f&lon=%f&units=metric&appid=%s",
                latitude, longitude, API_KEY
        );

        return sendRequest(urlString);
    }

    /**
     * Method for fetching weather data using One Call API 3.0
     * @param cityName the city's name of the target location
     * @return Return the coordination of the target location from the api
     */
    public JSONObject getCoordinates(String cityName) {
        JSONObject result = new JSONObject();

        final String urlString = String.format(
                "https://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s",
                cityName, API_KEY
        );

        final JSONArray responseArray = sendRequestArray(urlString);

        if (responseArray != null && !responseArray.isEmpty()) {
            // Return the first result in the array
            result = responseArray.getJSONObject(0);
        }
        return result;
    }

    // Private helper method to send HTTP GET request and parse JSON response
    private JSONObject sendRequest(String urlString) {
        JSONObject result = null;

        try {
            final URL url = new URL(urlString);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            final int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                final StringBuilder response = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                result = new JSONObject(response.toString());
            }
            else {
                System.err.println("Request failed. Response Code: " + responseCode);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    // Helper method to handle JSON array responses (for Geocoding API)
    private JSONArray sendRequestArray(String urlString) {
        JSONArray result = null;

        try {
            final URL url = new URL(urlString);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            final int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                final StringBuilder response = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                result = new JSONArray(response.toString());
            }
            else {
                System.err.println("Request failed. Response Code: " + responseCode);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
