package use_case.weather_hourly;

import org.json.JSONObject;

/**
 * The Weather Interactor.
 */
public class WeatherHourlyInteractor implements WeatherHourlyInputBoundary {
    private final WeatherHourlyDataAccessInterface weatherDataAccessObject;
    private final WeatherHourlyOutputBoundary weatherPresenter;
    private final String emptyString = "";
    private final int upperBound = 47;

    public WeatherHourlyInteractor(WeatherHourlyDataAccessInterface weatherDataAccessObject,
                             WeatherHourlyOutputBoundary weatherPresenter) {
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.weatherPresenter = weatherPresenter;
    }

    @Override
    public void execute(WeatherHourlyInputData weatherHourlyInputData) {
        final String inputCity = weatherHourlyInputData.getCity();
        final String inputHour = weatherHourlyInputData.getHour();

        final Boolean validHour = checkValidHour(inputHour);

        if (!emptyString.equals(inputCity)) {
            final JSONObject city = this.weatherDataAccessObject.getCoordinates(inputCity);

            if (!city.isEmpty()) {

                if (Boolean.TRUE.equals(validHour)) {
                    final int hour = Integer.parseInt(inputHour);

                    final double lat = city.getDouble("lat");
                    final double lon = city.getDouble("lon");

                    // Fetch weather data using the coordinates
                    final JSONObject weatherData = this.weatherDataAccessObject.getWeatherData(lat, lon);

                    final WeatherHourlyOutputData outputData = new WeatherHourlyOutputData(
                            weatherData.getJSONArray("hourly"),
                            hour,
                            city.getString("name"));

                    weatherPresenter.prepareSuccessView(outputData);
                }
                else {
                    weatherPresenter.prepareFailView("Please enter a valid hour between 0 and 47.");
                }
            }
            else {
                weatherPresenter.prepareFailView("Can't find your city.");
            }
        }
        else {
            weatherPresenter.prepareFailView("Please enter a city name.");
        }
    }

    private Boolean checkValidHour(String inputHour) {
        boolean validHour;
        try {
            Integer.parseInt(inputHour);
            validHour = 0 <= Integer.parseInt(inputHour) && Integer.parseInt(inputHour) < upperBound;
        }
        catch (NumberFormatException exception) {
            validHour = false;
        }
        return validHour;
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
