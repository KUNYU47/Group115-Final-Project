package use_case.weather_hourly;

import data_access.WeatherDataAccessObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherHourlyInteractorTest {

    @Test
    void successTest() {
        // Setup test data
        WeatherHourlyInputData inputData = new WeatherHourlyInputData("London", "5");
        WeatherHourlyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a success presenter
        WeatherHourlyOutputBoundary successPresenter = new WeatherHourlyOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherHourlyOutputData weatherHourlyOutputData) {
                // Check that the city and hour are correctly passed
                assertEquals("London", weatherHourlyOutputData.getCity());
                assertNotNull(weatherHourlyOutputData.getCondition());
                assertNotNull(weatherHourlyOutputData.getHourWeatherData());
                assertInstanceOf(Double.class, weatherHourlyOutputData.getTemperature());
                assertNotNull(weatherHourlyOutputData.getDescription());
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
            public void switchToDailyView() {
                // Expected call
            }
        };

        WeatherHourlyInputBoundary interactor = new WeatherHourlyInteractor(weatherDataAccess, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureCityNotFoundTest() {
        // Setup test data with a city that does not exist
        WeatherHourlyInputData inputData = new WeatherHourlyInputData("Nonexistent City", "5");
        WeatherHourlyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a failure presenter
        WeatherHourlyOutputBoundary failurePresenter = new WeatherHourlyOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherHourlyOutputData weatherHourlyOutputData) {
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
            public void switchToDailyView() {
                // Expected call
            }
        };

        WeatherHourlyInputBoundary interactor = new WeatherHourlyInteractor(weatherDataAccess, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyCityTest() {
        // Setup test data with an empty city name
        WeatherHourlyInputData inputData = new WeatherHourlyInputData("", "5");
        WeatherHourlyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a failure presenter
        WeatherHourlyOutputBoundary failurePresenter = new WeatherHourlyOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherHourlyOutputData weatherHourlyOutputData) {
                fail("Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Please enter a city name.", error);
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
            public void switchToDailyView() {
                // Expected call
            }
        };

        WeatherHourlyInputBoundary interactor = new WeatherHourlyInteractor(weatherDataAccess, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidHourTest() {
        // Setup test data with an invalid hour
        WeatherHourlyInputData inputData = new WeatherHourlyInputData("London", "50");
        WeatherHourlyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a failure presenter
        WeatherHourlyOutputBoundary failurePresenter = new WeatherHourlyOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherHourlyOutputData weatherHourlyOutputData) {
                fail("Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Please enter a valid hour between 0 and 47.", error);
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
            public void switchToDailyView() {
                // Expected call
            }
        };

        WeatherHourlyInputBoundary interactor = new WeatherHourlyInteractor(weatherDataAccess, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureNonNumericHourTest() {
        // Setup test data with a non-numeric hour
        WeatherHourlyInputData inputData = new WeatherHourlyInputData("London", "abc");
        WeatherHourlyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a failure presenter
        WeatherHourlyOutputBoundary failurePresenter = new WeatherHourlyOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherHourlyOutputData weatherHourlyOutputData) {
                fail("Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Please enter a valid hour between 0 and 47.", error);
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
            public void switchToDailyView() {
                // Expected call
            }
        };

        WeatherHourlyInputBoundary interactor = new WeatherHourlyInteractor(weatherDataAccess, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToLoggedInViewTest() {
        WeatherHourlyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // Wrapper class to capture the call to switchToLoggedInView
        class SwitchToLoggedInViewWrapper implements WeatherHourlyOutputBoundary {
            private boolean loggedInViewCalled = false;

            @Override
            public void prepareSuccessView(WeatherHourlyOutputData weatherHourlyOutputData) {
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
            public void switchToDailyView() {
                // Expected call
            }

            public boolean isLoggedInViewCalled() {
                return loggedInViewCalled;
            }
        }

        SwitchToLoggedInViewWrapper mockPresenter = new SwitchToLoggedInViewWrapper();
        WeatherHourlyInputBoundary interactor = new WeatherHourlyInteractor(weatherDataAccess, mockPresenter);
        interactor.switchToLoggedInView();

        assertTrue(mockPresenter.isLoggedInViewCalled(), "Expected switchToLoggedInView to be called.");
    }

    @Test
    void switchToCurrViewTest() {
        WeatherHourlyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // Wrapper class to capture the call to switchToCurrView
        class SwitchToCurrViewWrapper implements WeatherHourlyOutputBoundary {
            private boolean currViewCalled = false;

            @Override
            public void prepareSuccessView(WeatherHourlyOutputData weatherHourlyOutputData) {
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
            public void switchToDailyView() {
                // Expected call
            }

            public boolean isCurrViewCalled() {
                return currViewCalled;
            }
        }

        SwitchToCurrViewWrapper mockPresenter = new SwitchToCurrViewWrapper();
        WeatherHourlyInputBoundary interactor = new WeatherHourlyInteractor(weatherDataAccess, mockPresenter);
        interactor.switchToCurrView();

        assertTrue(mockPresenter.isCurrViewCalled(), "Expected switchToCurrView to be called.");
    }

    @Test
    void switchToDailyViewTest() {
        WeatherHourlyDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // Wrapper class to capture the call to switchToDailyView
        class SwitchToDailyViewWrapper implements WeatherHourlyOutputBoundary {
            private boolean dailyViewCalled = false;

            @Override
            public void prepareSuccessView(WeatherHourlyOutputData weatherHourlyOutputData) {
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
            public void switchToDailyView() {
                dailyViewCalled = true;
            }

            public boolean isDailyViewCalled() {
                return dailyViewCalled;
            }
        }

        SwitchToDailyViewWrapper mockPresenter = new SwitchToDailyViewWrapper();
        WeatherHourlyInputBoundary interactor = new WeatherHourlyInteractor(weatherDataAccess, mockPresenter);
        interactor.switchToDailyView();

        assertTrue(mockPresenter.isDailyViewCalled(), "Expected switchToDailyView to be called.");
    }
}
