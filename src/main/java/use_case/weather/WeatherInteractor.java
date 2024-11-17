package use_case.weather;

import data_access.WeatherDataAccessInterface;
import data_access.WeatherDataAcessObject;
import interface_adapter.weather.WeatherPresenter;
import org.json.JSONObject;

/**
 * The Weather Interactor.
 */
public class WeatherInteractor implements WeatherInputData {
    private final WeatherDataAccessInterface weatherDataAccessObject;
    private final WeatherOutputBoundary weatherPresenter;

    public WeatherInteractor(WeatherDataAccessInterface weatherDataAccessObject,
                             WeatherOutputBoundary weatherPresenter) {
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.weatherPresenter = weatherPresenter;
    }

    public void execute(WeatherInputData weatherInputData) {
        final String inputCity = weatherInputData.getCity();

        if (inputCity != null) {
            final WeatherDataAcessObject weatherDataAcessObject = new WeatherDataAcessObject();
            final JSONObject city = weatherDataAcessObject.getCoordinates(inputCity);

            if (city != null) {
                final double lat = city.getDouble("lat");
                final double lon = city.getDouble("lon");

                // Fetch weather data using the coordinates
                final JSONObject weatherData = WeatherDataAcessObject.getWeatherData(lat, lon);
                if (weatherData != null) {
                    System.out.println("Weather Data: " + weatherData.toString());
                }
                else {
                    System.out.println("Failed to fetch weather data.");
                }
            }
            else {
                System.out.println("Failed to fetch coordinates for the city.");
            }
        }
    }
}
