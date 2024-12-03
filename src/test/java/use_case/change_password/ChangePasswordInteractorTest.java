package use_case.change_password;

import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordInteractorTest {

    @Test
    void successPasswordChangeTest() {
        // Mock UserFactory
        UserFactory userFactory = (username, password) -> new User() {
            @Override
            public String getName() {
                return username;
            }

            @Override
            public String getPassword() {
                return password;
            }
        };

        // Mock Data Access Interface
        ChangePasswordUserDataAccessInterface mockDataAccess = new ChangePasswordUserDataAccessInterface() {
            @Override
            public void changePassword(User user) {
                assertEquals("testUser", user.getName());
                assertEquals("newPassword123", user.getPassword());
            }
        };

        // Mock Output Boundary
        ChangePasswordOutputBoundary mockPresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                assertEquals("testUser", outputData.getUsername()); // Ensure this matches the username
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

            @Override
            public void switchToChoosePetView() {
                // Expected call
            }
        };

        // Create the interactor
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(mockDataAccess, mockPresenter, userFactory);

        // Test execution
        ChangePasswordInputData inputData = new ChangePasswordInputData("testUser", "newPassword123");
        interactor.execute(inputData);
    }


    @Test
    void switchToCurrViewTest() {
        // Mock Output Boundary
        class MockPresenter implements ChangePasswordOutputBoundary {
            private boolean currViewCalled = false;

            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
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

            @Override
            public void switchToChoosePetView() {
                // Expected call
            }

            public boolean isCurrViewCalled() {
                return currViewCalled;
            }
        }

        MockPresenter mockPresenter = new MockPresenter();
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(null, mockPresenter, null);

        interactor.switchToCurrView();
        assertTrue(mockPresenter.isCurrViewCalled(), "Expected switchToCurrView to be called.");
    }

    @Test
    void switchToHourlyViewTest() {
        // Mock Output Boundary
        class MockPresenter implements ChangePasswordOutputBoundary {
            private boolean hourlyViewCalled = false;

            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
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

            @Override
            public void switchToChoosePetView() {
                // Expected call
            }

            public boolean isHourlyViewCalled() {
                return hourlyViewCalled;
            }
        }

        MockPresenter mockPresenter = new MockPresenter();
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(null, mockPresenter, null);

        interactor.switchToHourlyView();
        assertTrue(mockPresenter.isHourlyViewCalled(), "Expected switchToHourlyView to be called.");
    }

    @Test
    void switchToDailyViewTest() {
        // Mock Output Boundary
        class MockPresenter implements ChangePasswordOutputBoundary {
            private boolean dailyViewCalled = false;

            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
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

            @Override
            public void switchToChoosePetView() {
                // Expected call
            }

            public boolean isDailyViewCalled() {
                return dailyViewCalled;
            }
        }

        MockPresenter mockPresenter = new MockPresenter();
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(null, mockPresenter, null);

        interactor.switchToDailyView();
        assertTrue(mockPresenter.isDailyViewCalled(), "Expected switchToDailyView to be called.");
    }

    @Test
    void switchToChoosePetViewTest() {
        // Mock Output Boundary
        class MockPresenter implements ChangePasswordOutputBoundary {
            private boolean choosePetViewCalled = false;

            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
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
                // Expected call
            }

            @Override
            public void switchToChoosePetView() {
                choosePetViewCalled = true;
            }

            public boolean isChoosePetViewCalled() {
                return choosePetViewCalled;
            }
        }

        MockPresenter mockPresenter = new MockPresenter();
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(null, mockPresenter, null);

        interactor.switchToChoosePetView();
        assertTrue(mockPresenter.isChoosePetViewCalled(), "Expected switchToChoosePetView to be called.");
    }
}
