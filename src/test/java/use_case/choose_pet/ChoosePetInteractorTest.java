package use_case.choose_pet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChoosePetInteractorTest {

    @Test
    void validPetSelectionTest() {
        // Setup test data
        ChoosePetInputData inputData = new ChoosePetInputData("Dog");

        // Mock the output boundary
        ChoosePetOutputBoundary successPresenter = new ChoosePetOutputBoundary() {
            @Override
            public void prepareSuccessView(ChoosePetOutputData outputData) {
                assertEquals("Dog", outputData.getSelectedPet());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected failure: " + error);
            }

            @Override
            public void switchToCurrView() {
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

        // Execute the interactor
        ChoosePetInputBoundary interactor = new ChoosePetInteractor(successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void invalidPetSelectionTest() {
        // Setup test data with an invalid pet
        ChoosePetInputData inputData = new ChoosePetInputData("Dragon");

        // Mock the output boundary
        ChoosePetOutputBoundary failurePresenter = new ChoosePetOutputBoundary() {
            @Override
            public void prepareSuccessView(ChoosePetOutputData outputData) {
                fail("Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid pet selection. Please select a valid pet from: [Dog, Cat, Rabbit, Fox]", error);
            }

            @Override
            public void switchToCurrView() {
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

        // Execute the interactor
        ChoosePetInputBoundary interactor = new ChoosePetInteractor(failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void emptyPetSelectionTest() {
        // Setup test data with an empty selection
        ChoosePetInputData inputData = new ChoosePetInputData("");

        // Mock the output boundary
        ChoosePetOutputBoundary failurePresenter = new ChoosePetOutputBoundary() {
            @Override
            public void prepareSuccessView(ChoosePetOutputData outputData) {
                fail("Unexpected success.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid pet selection. Please select a valid pet from: [Dog, Cat, Rabbit, Fox]", error);
            }

            @Override
            public void switchToCurrView() {
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

        // Execute the interactor
        ChoosePetInputBoundary interactor = new ChoosePetInteractor(failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToCurrViewTest() {
        // Mock the output boundary
        class SwitchToCurrViewWrapper implements ChoosePetOutputBoundary {
            private boolean currViewCalled = false;

            @Override
            public void prepareSuccessView(ChoosePetOutputData outputData) {
                fail("Unexpected call to prepareSuccessView.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected call to prepareFailView.");
            }

            @Override
            public void switchToCurrView() {
                currViewCalled = true;
            }

            @Override
            public void switchToHourlyView() {
                // Expected call
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
        ChoosePetInputBoundary interactor = new ChoosePetInteractor(mockPresenter);
        interactor.switchToCurrView();

        assertTrue(mockPresenter.isCurrViewCalled(), "Expected switchToCurrView to be called.");
    }

    @Test
    void switchToHourlyViewTest() {
        // Mock the output boundary
        class SwitchToHourlyViewWrapper implements ChoosePetOutputBoundary {
            private boolean hourlyViewCalled = false;

            @Override
            public void prepareSuccessView(ChoosePetOutputData outputData) {
                fail("Unexpected call to prepareSuccessView.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected call to prepareFailView.");
            }

            @Override
            public void switchToCurrView() {
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
        ChoosePetInputBoundary interactor = new ChoosePetInteractor(mockPresenter);
        interactor.switchToHourlyView();

        assertTrue(mockPresenter.isHourlyViewCalled(), "Expected switchToHourlyView to be called.");
    }

    @Test
    void switchToDailyViewTest() {
        class SwitchToDailyViewWrapper implements ChoosePetOutputBoundary {
            private boolean dailyViewCalled = false;

            @Override
            public void prepareSuccessView(ChoosePetOutputData outputData) {
                fail("Unexpected call to prepareSuccessView.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected call to prepareFailView.");
            }

            @Override
            public void switchToCurrView() {
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
        ChoosePetInputBoundary interactor = new ChoosePetInteractor(mockPresenter);
        interactor.switchToDailyView();

        assertTrue(mockPresenter.isDailyViewCalled(), "Expected switchToDailyView to be called.");
    }
}