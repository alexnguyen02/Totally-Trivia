package use_case.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "password");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Paul", user.getUsername());
                assertNotNull(user.getCreationTime()); // any creation time is fine.
                assertTrue(userRepository.existsByName("Paul"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        CommonUserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Paul", "password",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");

        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonUserFactory(), user);
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };
        CommonUserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Paul", "password",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory(), user);
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the repo so that when we check later they already exist
        CommonUserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Paul", "password",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");

        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory(), user);
        interactor.execute(inputData);
    }
}