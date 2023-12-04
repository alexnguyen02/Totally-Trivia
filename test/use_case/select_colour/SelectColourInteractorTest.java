package use_case.select_colour;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.fail;

public class SelectColourInteractorTest {

    @Test
    public void successTest() throws IOException {
        CommonUserFactory userFactory = new CommonUserFactory();

        User user = userFactory.create("Rob", "Johnson", LocalDateTime.parse("2023-12-03T19:42:37.375"), 42, "Orange");

        SelectColourInputData inputData = new SelectColourInputData(user.getColourScheme());

        SelectColourUserDataAccessInterface selectColourUserDataAccessInterface = new InMemoryUserDataAccessObject();
        selectColourUserDataAccessInterface.save(user);

        SelectColourOutputBoundary successPresenter = new SelectColourOutputBoundary() {
            @Override
            public void prepareSuccessView(SelectColourOutputData selectColourOutputData) {
                Assertions.assertEquals("Orange", selectColourOutputData.getColour());
            }

            @Override
            public void prepareFailView(String error) {
                fail(error);
            }
        };

        SelectColourInputBoundary selectColourInteractor = new SelectColourInteractor(successPresenter, user, selectColourUserDataAccessInterface);
        selectColourInteractor.execute(inputData);
    }

    @Test
    public void failureColourNotUnlockedTest() {
        SelectColourInputData inputData = new SelectColourInputData("Black");

        CommonUserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Jess", "Jackson", LocalDateTime.parse("2023-11-23T12:25:42.425"), 12, "Black");

        SelectColourUserDataAccessInterface selectColourUserDataAccessInterface = new InMemoryUserDataAccessObject();

        SelectColourOutputBoundary failurePresenter = new SelectColourOutputBoundary() {
            @Override
            public void prepareSuccessView(SelectColourOutputData selectColourOutputData) {
                Assertions.fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                Assertions.assertEquals("This colour has not been unlocked yet.", error);
            }
        };

        SelectColourInputBoundary selectColourInteractor = new SelectColourInteractor(failurePresenter, user, selectColourUserDataAccessInterface);
        selectColourInteractor.execute(inputData);
    }
}
