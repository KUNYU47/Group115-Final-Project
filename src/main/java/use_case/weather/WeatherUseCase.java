package use_case.weather;

import org.json.JSONObject;

import api.ApiService;

public class WeatherUseCase {
    private ApiService apiService;

    public WeatherUseCase() {
        this.apiService = new ApiService();
    }

    /**
     * Method
     *
     * @param cityName the city's name of the target location
     */
    public void printWeatherData(String cityName) {
        // Fetch coordinates for the city
        final JSONObject geoData = apiService.getCoordinates(cityName);

        if (geoData != null) {
            final double lat = geoData.getDouble("lat");
            final double lon = geoData.getDouble("lon");
            System.out.println("Coordinates: Lat=" + lat + ", Lon=" + lon);

            // Fetch weather data using the coordinates
            final JSONObject weatherData = apiService.getWeatherData(lat, lon);
            if (weatherData != null) {
                System.out.println("Weather Data: " + weatherData.toString());
                // Additional parsing or processing can be done here
            } else {
                System.out.println("Failed to fetch weather data.");
            }
        } else {
            System.out.println("Failed to fetch coordinates for the city.");
        }
    }

    public static void main(String[] args) {
        final WeatherUseCase weatherUseCase = new WeatherUseCase();
        final String cityName = "Toronto";
        weatherUseCase.printWeatherData(cityName);
    }
}