package use_case.weather_daily;

import org.json.JSONObject;

public class WeatherDailyInteractor implements WeatherDailyInputBoundary {

    private final WeatherDailyDataAccessInterface weatherDailyDataAccessObject;
    private final WeatherDailyOutputBoundary weatherPresenter;
    private final String emptyString = "";
    private final int dummyInt = -1;
    private final int upperBound = 7;

    public WeatherDailyInteractor(WeatherDailyDataAccessInterface weatherDailyDataAccessObject,
                                  WeatherDailyOutputBoundary weatherPresenter) {
        this.weatherDailyDataAccessObject = weatherDailyDataAccessObject;
        this.weatherPresenter = weatherPresenter;
    }

    @Override
    public void execute(WeatherDailyInputData weatherDailyInputData) {
        final String inputCity = weatherDailyInputData.getCity();
        final String inputDay = weatherDailyInputData.getDay();

        final Boolean validDay = checkValidDay(inputDay);

        if (!emptyString.equals(inputCity)) {

            final JSONObject city = this.weatherDailyDataAccessObject.getCoordinates(inputCity);

            if (!city.isEmpty()) {

                if (Boolean.TRUE.equals(validDay)) {
                    final int day = Integer.parseInt(inputDay);

                    final double lat = city.getDouble("lat");
                    final double lon = city.getDouble("lon");

                    // Fetch weather data using the coordinates
                    final JSONObject weatherData = this.weatherDailyDataAccessObject.getWeatherData(lat, lon);

                    final WeatherDailyOutputData outputData = new WeatherDailyOutputData(
                            weatherData.getJSONArray("daily"),
                            day,
                            city.getString("name"),
                            false);

                    weatherPresenter.prepareSuccessView(outputData);
                }
                else {
                    weatherPresenter.prepareFailView("Please enter a valid day between 0 and 7.");
                }
            }
            else {
                weatherPresenter.prepareFailView("Can't find your city.");
            }
        }
        else {
            weatherPresenter.prepareFailView("Please enter a city.");
        }
    }

    private Boolean checkValidDay(String day) {
        boolean valid;
        try {
            Integer.parseInt(day);
            valid = 0 <= Integer.parseInt(day) && Integer.parseInt(day) <= this.upperBound;
        }
        catch (NumberFormatException exception) {
            valid = false;
        }
        return valid;
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
    public void switchToHourlyView() {
        weatherPresenter.switchToHourlyView();
    }
}
