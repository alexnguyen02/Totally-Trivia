package use_case.login;

import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    private static class StubUserDataAccess implements LoginUserDataAccessInterface {
        private final User user;

        public StubUserDataAccess(User user) {
            this.user = user;
        }

        @Override
        public boolean existsByName(String username) {
            return user != null && user.getName().equals(username);
        }

        @Override
        public void save(User user) {

        }

        @Override
        public User get(String username) {
            return user;
        }
    }

    private static class StubLoginPresenter implements LoginOutputBoundary {
        private String failViewMessage;
        private boolean successViewCalled;

        @Override
        public void prepareFailView(String message) {
            failViewMessage = message;
        }

        @Override
        public void prepareSuccessView(LoginOutputData outputData) {
            successViewCalled = true;
        }

        public String getFailViewMessage() {
            return failViewMessage;
        }

        public boolean isSuccessViewCalled() {
            return successViewCalled;
        }
    }

    @Test
    void testExecuteAccountDoesNotExist() {
        // Create a stub for LoginUserDataAccessInterface
        StubUserDataAccess userDataAccessStub = new StubUserDataAccess(null);

        // Create a stub for LoginOutputBoundary
        StubLoginPresenter loginPresenterStub = new StubLoginPresenter();

        // Create a User for the logged-in user
        CommonUserFactory userFactory = new CommonUserFactory();
        User loggedUser = userFactory.create("1", "2", LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");


        // Create LoginInteractor with the stubs
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessStub, loginPresenterStub, loggedUser);

        // Create LoginInputData with a nonexistent username
        LoginInputData loginInputData = new LoginInputData("nonexistentUser", "password");

        // Call execute with the test data
        loginInteractor.execute(loginInputData);

        // Verify that prepareFailView is called with the correct message
        assertEquals("nonexistentUser: Account does not exist.", loginPresenterStub.getFailViewMessage());

        // Verify that successView is not called
        assertFalse(loginPresenterStub.isSuccessViewCalled());
    }

    @Test
    void testExecuteIncorrectPassword() {
        CommonUserFactory userFactory = new CommonUserFactory();
        User loggedUser = userFactory.create("1", "2", LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");


        // Create a stub for LoginUserDataAccessInterface
        StubUserDataAccess userDataAccessStub = new StubUserDataAccess(loggedUser);

        // Create a stub for LoginOutputBoundary
        StubLoginPresenter loginPresenterStub = new StubLoginPresenter();

        // Create a User for the logged-in user


        // Create LoginInteractor with the stubs
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessStub, loginPresenterStub, loggedUser);

        // Create LoginInputData with an incorrect password
        LoginInputData loginInputData = new LoginInputData("1", "1");

        loginInteractor.execute(loginInputData);

        // Verify that prepareFailView is called with the correct message
        assertEquals("Incorrect password for 1.", loginPresenterStub.getFailViewMessage());

        // Verify that successView is not called
        assertFalse(loginPresenterStub.isSuccessViewCalled());
    }

    @Test
    void testExecuteSuccess() {
        // Create a stub for LoginUserDataAccessInterface
        CommonUserFactory userFactory = new CommonUserFactory();
        User loggedUser = userFactory.create("1", "2", LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");


        StubUserDataAccess userDataAccessStub = new StubUserDataAccess(loggedUser);

        // Create a stub for LoginOutputBoundary
        StubLoginPresenter loginPresenterStub = new StubLoginPresenter();

        // Create a User for the logged-in user


        // Create LoginInteractor with the stubs
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessStub, loginPresenterStub, loggedUser);

        // Create LoginInputData with correct credentials
        LoginInputData loginInputData = new LoginInputData("1", "2");

        loginInteractor.execute(loginInputData);

        // Verify that successView is called
        assertTrue(loginPresenterStub.isSuccessViewCalled());
    }
}