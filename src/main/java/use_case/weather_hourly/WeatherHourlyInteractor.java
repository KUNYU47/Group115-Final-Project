package use_case.weather_hourly;

import org.json.JSONObject;

/**
 * The Weather Interactor.
 */
public class WeatherHourlyInteractor implements WeatherHourlyInputBoundary {
    private final WeatherHourlyDataAccessInterface weatherDataAccessObject;
    private final WeatherHourlyOutputBoundary weatherPresenter;
    private final String emptyString = "";

    public WeatherHourlyInteractor(WeatherHourlyDataAccessInterface weatherDataAccessObject,
                             WeatherHourlyOutputBoundary weatherPresenter) {
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.weatherPresenter = weatherPresenter;
    }

    @Override
    public void execute(WeatherHourlyInputData weatherHourlyInputData) {
        final String inputCity = weatherHourlyInputData.getCity();

        if (!emptyString.equals(inputCity)) {
            final JSONObject city = this.weatherDataAccessObject.getCoordinates(inputCity);

            if (!city.isEmpty()) {
                final double lat = city.getDouble("lat");
                final double lon = city.getDouble("lon");

                // Fetch weather data using the coordinates
                final JSONObject weatherData = this.weatherDataAccessObject.getWeatherData(lat, lon);

                final WeatherHourlyOutputData outputData = new WeatherHourlyOutputData(
                        weatherData.getJSONArray("hourly"),
                        city.getString("name"),
                        false);

                weatherPresenter.prepareSuccessView(outputData);

            }
            else {
                weatherPresenter.prepareFailView("Can't find your city.");
            }
        }
        else {
            weatherPresenter.prepareFailView("Please enter a city name.");
        }
    }

    @Override
    public void switchToLoggedInView() {
        weatherPresenter.switchToLoggedInView();
    }

    @Override
    public void switchToCurrView() {
        weatherPresenter.switchToCurrView();
    }

    @Override
    public void switchToDailyView() {
        weatherPresenter.switchToDailyView();
    }

}
