package use_case.weather;

import data_access.WeatherDataAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherInteractorTest {

    @Test
    void successTest() {
        // Setup test data
        WeatherInputData inputData = new WeatherInputData("London");
        WeatherDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a success presenter
        WeatherOutputBoundary successPresenter = new WeatherOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherOutputData weatherOutputData) {
                // Check that the weather data is correctly passed
                assertEquals("London", weatherOutputData.getCity());
                assertInstanceOf(Double.class, weatherOutputData.getTemperature());
                assertNotNull(weatherOutputData.getCurrWeatherData());
                assertNotNull(weatherOutputData.getCondition());
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
            public void switchToHourlyView() {
                // Expected call
            }

            @Override
            public void switchToDailyView() {
                // Expected call
            }
        };

        WeatherInputBoundary interactor = new WeatherInteractor(weatherDataAccess, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureCityNotFoundTest() {
        // Setup test data with a city that does not exist
        WeatherInputData inputData = new WeatherInputData("Nonexistent City");
        WeatherDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a failure presenter
        WeatherOutputBoundary failurePresenter = new WeatherOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherOutputData weatherOutputData) {
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
            public void switchToHourlyView() {
                // Expected call
            }

            @Override
            public void switchToDailyView() {
                // Expected call
            }
        };

        WeatherInputBoundary interactor = new WeatherInteractor(weatherDataAccess, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyCityTest() {
        // Setup test data with an empty city name
        WeatherInputData inputData = new WeatherInputData("");
        WeatherDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // This creates a failure presenter
        WeatherOutputBoundary failurePresenter = new WeatherOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherOutputData weatherOutputData) {
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
            public void switchToHourlyView() {
                // Expected call
            }

            @Override
            public void switchToDailyView() {
                // Expected call
            }
        };

        WeatherInputBoundary interactor = new WeatherInteractor(weatherDataAccess, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToLoggedInViewTest() {
        WeatherDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // Wrapper class to capture the call to switchToLoggedInView
        class SwitchToLoggedInViewWrapper implements WeatherOutputBoundary {
            private boolean loggedInViewCalled = false;

            @Override
            public void prepareSuccessView(WeatherOutputData weatherOutputData) {
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
            public void switchToHourlyView() {
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
        WeatherInputBoundary interactor = new WeatherInteractor(weatherDataAccess, mockPresenter);
        interactor.switchToLoggedInView();

        assertTrue(mockPresenter.isLoggedInViewCalled(), "Expected switchToLoggedInView to be called.");
    }

    @Test
    void switchToHourlyViewTest() {
        WeatherDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // Wrapper class to capture the call to switchToHourlyView
        class SwitchToHourlyViewWrapper implements WeatherOutputBoundary {
            private boolean hourlyViewCalled = false;

            @Override
            public void prepareSuccessView(WeatherOutputData weatherOutputData) {
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
            public void switchToHourlyView() {
                hourlyViewCalled = true;
            }

            @Override
            public void switchToDailyView() {
                // Expected call
            }

            public boolean isHourlyViewCalled() {
                return hourlyViewCalled;
            }
        }

        SwitchToHourlyViewWrapper mockPresenter = new SwitchToHourlyViewWrapper();
        WeatherInputBoundary interactor = new WeatherInteractor(weatherDataAccess, mockPresenter);
        interactor.switchToHourlyView();

        assertTrue(mockPresenter.isHourlyViewCalled(), "Expected switchToHourlyView to be called.");
    }

    @Test
    void switchToDailyViewTest() {
        WeatherDataAccessInterface weatherDataAccess = new WeatherDataAccessObject();

        // Wrapper class to capture the call to switchToDailyView
        class SwitchToDailyViewWrapper implements WeatherOutputBoundary {
            private boolean dailyViewCalled = false;

            @Override
            public void prepareSuccessView(WeatherOutputData weatherOutputData) {
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
            public void switchToHourlyView() {
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
        WeatherInputBoundary interactor = new WeatherInteractor(weatherDataAccess, mockPresenter);
        interactor.switchToDailyView();

        assertTrue(mockPresenter.isDailyViewCalled(), "Expected switchToDailyView to be called.");
    }
}
