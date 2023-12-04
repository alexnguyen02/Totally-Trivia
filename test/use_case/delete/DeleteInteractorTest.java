package use_case.delete;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.delete.*;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DeleteInteractorTest {

    @Test
    void successTest() {
        CommonUserFactory userFactory = new CommonUserFactory();
        User user1= userFactory.create("user_1", "1",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");

        User user2 = userFactory.create("user_2", "2",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");

        User user3 = userFactory.create("user_3", "3",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");

        HashMap<String,User> map = new HashMap<String,User>();
        map.put("user_1", user1);
        map.put("user_2", user2);
        map.put("user_3", user3);

        DeleteUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject() {

            @Override
            public String delete(String username) {
                CommonUserFactory userFactory = new CommonUserFactory();
                User user1= userFactory.create("user_1", "1",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");

                User user2 = userFactory.create("user_2", "2",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");

                User user3 = userFactory.create("user_3", "3",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");

                HashMap<String,User> map = new HashMap<String,User>();
                map.put("user_1", user1);
                map.put("user_2", user2);
                map.put("user_3", user3);
                if (map.containsKey(username)) {
                    map.remove(username);
                    return username;
                } else {
                    return "User not found";
                }
            }
        };

        // This creates a successPresenter that tests whether the test case is as we expect.
        DeleteOutputBoundary successPresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteOutputData user2) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("You are successful delete account: user_2", user2.getUsernames());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        DeleteInputBoundary interactor = new DeleteInteractor(userRepository, user2, successPresenter);
        interactor.execute("user_2");
    }

//    @Test
//    void failurePasswordMismatchTest() {
//        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
//        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
//
//        // This creates a presenter that tests whether the test case is as we expect.
//        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
//            @Override
//            public void prepareSuccessView(SignupOutputData user) {
//                // 2 things to check: the output data is correct, and the user has been created in the DAO.
//                fail("Use case success is unexpected.");
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                assertEquals("Passwords don't match.", error);
//            }
//        };
//
//        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
//        interactor.execute(inputData);
//    }
//
//    @Test
//    void failureUserExistsTest() {
//        SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
//        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
//
//        // Add Paul to the repo so that when we check later they already exist
//        UserFactory factory = new CommonUserFactory();
//        User user = factory.create("Paul", "pwd",  LocalDateTime.now());
//        userRepository.save(user);
//
//        // This creates a presenter that tests whether the test case is as we expect.
//        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
//            @Override
//            public void prepareSuccessView(SignupOutputData user) {
//                // 2 things to check: the output data is correct, and the user has been created in the DAO.
//                fail("Use case success is unexpected.");
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                assertEquals("User already exists.", error);
//            }
//        };
//
//        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonUserFactory());
//        interactor.execute(inputData);
//    }
}