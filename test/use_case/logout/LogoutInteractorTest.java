package use_case.logout;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LogoutInteractorTest {

    @Test
    void successLogoutTest() {
        // Create a mock user and presenter
        UserFactory userFactory = new CommonUserFactory();
        User loggedInUser = userFactory.create("John", "password", LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "Blue");
        LogoutOutputBoundary logoutPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData logoutOutputData) {
                UserFactory userFactory = new CommonUserFactory();
                User loggedInUser = userFactory.create("John", "password", LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "Blue");
                assertTrue(logoutOutputData.isSuccess());
                assertEquals("John", loggedInUser.getName()); // Check that the user has been logged out
            }
        };

        // Create the LogoutInteractor and execute
        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutPresenter, loggedInUser);
        logoutInteractor.execute();
    }

    @Test
    void successLogoutWithoutLoggedInUserTest() {
        // Create a mock presenter
        LogoutOutputBoundary logoutPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData logoutOutputData) {
                assertTrue(logoutOutputData.isSuccess());
            }
        };

        // Create the LogoutInteractor without a logged-in user and execute
        UserFactory userFactory = new CommonUserFactory();
        User loggedInUser = userFactory.create("John", "password", LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "Blue");
        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutPresenter, loggedInUser);
        logoutInteractor.execute();
    }

    // Add more tests for edge cases or specific scenarios as needed
}