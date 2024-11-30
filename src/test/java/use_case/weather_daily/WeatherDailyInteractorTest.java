package use_case.weather_daily;

import data_access.WeatherDataAccessObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDailyInteractorTest {

    @Test
    void successTest() {
        // Setup test data
        WeatherDailyInputData inputData = new WeatherDailyInputData("London", "3");
        WeatherDailyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a success presenter
        WeatherDailyOutputBoundary successPresenter = new WeatherDailyOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherDailyOutputData weatherDailyOutputData) {
                // Check that the city and day are correctly passed
                assertEquals("London", weatherDailyOutputData.getCity());
                assertNotNull(weatherDailyOutputData.getDayWeatherData());
                assertNotNull(weatherDailyOutputData.getCondition());
                assertInstanceOf(Double.class, weatherDailyOutputData.getTemperature());
                assertNotNull(weatherDailyOutputData.getSummary());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected failure: " + error);
            }

            @Override
            public void switchToLoggedInView() {
                // Expected call
            }

            @Override
            public void switchToCurrView() {
                // Expected call
            }

            @Override
            public void switchToHourlyView() {
                // Expected call
            }
        };

        WeatherDailyInputBoundary interactor = new WeatherDailyInteractor(weatherDataAccess, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureCityNotFoundTest() {
        // Setup test data with a city that does not exist
        WeatherDailyInputData inputData = new WeatherDailyInputData("Nonexistent City", "3");
        WeatherDailyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a failure presenter
        WeatherDailyOutputBoundary failurePresenter = new WeatherDailyOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherDailyOutputData weatherDailyOutputData) {
                fail("Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Can't find your city.", error);
            }

            @Override
            public void switchToLoggedInView() {
                // Expected call
            }

            @Override
            public void switchToCurrView() {
                // Expected call
            }

            @Override
            public void switchToHourlyView() {
                // Expected call
            }
        };

        WeatherDailyInputBoundary interactor = new WeatherDailyInteractor(weatherDataAccess, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyCityTest() {
        // Setup test data with an empty city name
        WeatherDailyInputData inputData = new WeatherDailyInputData("", "3");
        WeatherDailyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a failure presenter
        WeatherDailyOutputBoundary failurePresenter = new WeatherDailyOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherDailyOutputData weatherDailyOutputData) {
                fail("Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Please enter a city.", error);
            }

            @Override
            public void switchToLoggedInView() {
                // Expected call
            }

            @Override
            public void switchToCurrView() {
                // Expected call
            }

            @Override
            public void switchToHourlyView() {
                // Expected call
            }
        };

        WeatherDailyInputBoundary interactor = new WeatherDailyInteractor(weatherDataAccess, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidDayTest() {
        // Setup test data with an invalid day
        WeatherDailyInputData inputData = new WeatherDailyInputData("London", "10");
        WeatherDailyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a failure presenter
        WeatherDailyOutputBoundary failurePresenter = new WeatherDailyOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherDailyOutputData weatherDailyOutputData) {
                fail("Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Please enter a valid day between 0 and 7.", error);
            }

            @Override
            public void switchToLoggedInView() {
                // Expected call
            }

            @Override
            public void switchToCurrView() {
                // Expected call
            }

            @Override
            public void switchToHourlyView() {
                // Expected call
            }
        };

        WeatherDailyInputBoundary interactor = new WeatherDailyInteractor(weatherDataAccess, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureNonNumericDayTest() {
        // Setup test data with a non-numeric day
        WeatherDailyInputData inputData = new WeatherDailyInputData("London", "abc");
        WeatherDailyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a failure presenter
        WeatherDailyOutputBoundary failurePresenter = new WeatherDailyOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherDailyOutputData weatherDailyOutputData) {
                fail("Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Please enter a valid day between 0 and 7.", error);
            }

            @Override
            public void switchToLoggedInView() {
                // Expected call
            }

            @Override
            public void switchToCurrView() {
                // Expected call
            }

            @Override
            public void switchToHourlyView() {
                // Expected call
            }
        };

        WeatherDailyInputBoundary interactor = new WeatherDailyInteractor(weatherDataAccess, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToLoggedInViewTest() {
        WeatherDailyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // Wrapper class to capture the call to switchToLoggedInView
        class SwitchToLoggedInViewWrapper implements WeatherDailyOutputBoundary {
            private boolean loggedInViewCalled = false;

            @Override
            public void prepareSuccessView(WeatherDailyOutputData weatherDailyOutputData) {
                fail("Unexpected call to prepareSuccessView.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected call to prepareFailView.");
            }

            @Override
            public void switchToLoggedInView() {
                loggedInViewCalled = true;
            }

            @Override
            public void switchToCurrView() {
                // Expected call
            }

            @Override
            public void switchToHourlyView() {
                // Expected call
            }

            public boolean isLoggedInViewCalled() {
                return loggedInViewCalled;
            }
        }

        SwitchToLoggedInViewWrapper mockPresenter = new SwitchToLoggedInViewWrapper();
        WeatherDailyInputBoundary interactor = new WeatherDailyInteractor(weatherDataAccess, mockPresenter);
        interactor.switchToLoggedInView();

        assertTrue(mockPresenter.isLoggedInViewCalled(), "Expected switchToLoggedInView to be called.");
    }

    @Test
    void switchToCurrViewTest() {
        WeatherDailyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // Wrapper class to capture the call to switchToCurrView
        class SwitchToCurrViewWrapper implements WeatherDailyOutputBoundary {
            private boolean currViewCalled = false;

            @Override
            public void prepareSuccessView(WeatherDailyOutputData weatherDailyOutputData) {
                fail("Unexpected call to prepareSuccessView.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected call to prepareFailView.");
            }

            @Override
            public void switchToLoggedInView() {
                // Expected call
            }

            @Override
            public void switchToCurrView() {
                currViewCalled = true;
            }

            @Override
            public void switchToHourlyView() {
                // Expected call
            }

            public boolean isCurrViewCalled() {
                return currViewCalled;
            }
        }

        SwitchToCurrViewWrapper mockPresenter = new SwitchToCurrViewWrapper();
        WeatherDailyInputBoundary interactor = new WeatherDailyInteractor(weatherDataAccess, mockPresenter);
        interactor.switchToCurrView();

        assertTrue(mockPresenter.isCurrViewCalled(), "Expected switchToCurrView to be called.");
    }

    @Test
    void switchToHourlyViewTest() {
        WeatherDailyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // Wrapper class to capture the call to switchToHourlyView
        class SwitchToHourlyViewWrapper implements WeatherDailyOutputBoundary {
            private boolean hourlyViewCalled = false;

            @Override
            public void prepareSuccessView(WeatherDailyOutputData weatherDailyOutputData) {
                fail("Unexpected call to prepareSuccessView.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected call to prepareFailView.");
            }

            @Override
            public void switchToLoggedInView() {
                // Expected call
            }

            @Override
            public void switchToCurrView() {
                // Expected call
            }

            @Override
            public void switchToHourlyView() {
                hourlyViewCalled = true;
            }

            public boolean isHourlyViewCalled() {
                return hourlyViewCalled;
            }
        }

        SwitchToHourlyViewWrapper mockPresenter = new SwitchToHourlyViewWrapper();
        WeatherDailyInputBoundary interactor = new WeatherDailyInteractor(weatherDataAccess, mockPresenter);
        interactor.switchToHourlyView();

        assertTrue(mockPresenter.isHourlyViewCalled(), "Expected switchToHourlyView to be called.");
    }
}
