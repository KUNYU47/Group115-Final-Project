package use_case.weather;

import org.json.JSONObject;

import data_access.WeatherDataAcessObject;

/**
 * The Weather Interactor.
 */
public class WeatherInteractor implements WeatherInputBoundary {
    private final WeatherDataAccessInterface weatherDataAccessObject;
    private final WeatherOutputBoundary weatherPresenter;

    public WeatherInteractor(WeatherDataAccessInterface weatherDataAccessObject,
                             WeatherOutputBoundary weatherPresenter) {
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.weatherPresenter = weatherPresenter;
    }

    @Override
    public void execute(WeatherInputData weatherInputData) {
        final String inputCity = weatherInputData.getCity();

        if (inputCity != null) {
            final JSONObject city = this.weatherDataAccessObject.getCoordinates(inputCity);

            if (city != null) {
                final double lat = city.getDouble("lat");
                final double lon = city.getDouble("lon");

                // Fetch weather data using the coordinates
                final JSONObject weatherData = this.weatherDataAccessObject.getWeatherData(lat, lon);

                final WeatherOutputData response = new WeatherOutputData(
                        weatherData.getJSONObject("current"),
                        city.getString("name"),
                        false);

                weatherPresenter.prepareSuccessView(response);

            }
            else {
                weatherPresenter.prepareFailView("Can't find your city.");
            }
        }
        else {
            weatherPresenter.prepareFailView("Invalid City.");
        }
    }
}
