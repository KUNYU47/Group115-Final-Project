package use_case.weather;

import org.json.JSONObject;

/**
 * The Weather Interactor.
 */
public class WeatherInteractor implements WeatherInputBoundary {
    private final WeatherDataAccessInterface weatherDataAccessObject;
    private final WeatherOutputBoundary weatherPresenter;
    private final String emptyString = "";

    public WeatherInteractor(WeatherDataAccessInterface weatherDataAccessObject,
                             WeatherOutputBoundary weatherPresenter) {
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.weatherPresenter = weatherPresenter;
    }

    @Override
    public void execute(WeatherInputData weatherInputData) {
        final String inputCity = weatherInputData.getCity();

        if (!emptyString.equals(inputCity)) {
            final JSONObject city = this.weatherDataAccessObject.getCoordinates(inputCity);

            if (!city.isEmpty()) {
                final double lat = city.getDouble("lat");
                final double lon = city.getDouble("lon");

                // Fetch weather data using the coordinates
                final JSONObject weatherData = this.weatherDataAccessObject.getWeatherData(lat, lon);

                final WeatherOutputData outputData = new WeatherOutputData(
                        weatherData.getJSONObject("current"),
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
    public void switchToHourlyView() {
        weatherPresenter.switchToHourlyView();
    }

    @Override
    public void switchToDailyView() {
        weatherPresenter.switchToDailyView();
    }

}
