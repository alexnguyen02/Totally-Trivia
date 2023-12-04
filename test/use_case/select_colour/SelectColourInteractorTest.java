package use_case.select_colour;

import data_access.FileUserDataAccessObject;
import data_access.InMemorySelectModeAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Question;
import entity.User;
import use_case.select_colour.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SelectColourInteractorTest {
    @org.junit.Test
    public void successTest() throws IOException {
        SelectColourInputData inputData = new SelectColourInputData("Orange");

        CommonUserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Rob", "Johnson", LocalDateTime.parse("2023-12-03T19:42:37.375"), 42, "Orange");

        SelectColourUserDataAccessInterface selectColourUserDataAccessInterface = new InMemoryUserDataAccessObject();

        SelectColourOutputBoundary successPresenter = new SelectColourOutputBoundary() {
            @Override
            public void prepareSuccessView(SelectColourOutputData selectColourOutputData) {
                assertEquals("Orange", user.getColourScheme());
            }

            @Override
            public void prepareFailView(String error) {
                fail(error);
            }
        };

        SelectColourInputBoundary selectColourInteractor = new SelectColourInteractor(successPresenter, user, selectColourUserDataAccessInterface);
        selectColourInteractor.execute(inputData);
    }

    @org.junit.Test
    public void failurePasswordMismatchTest() {
        SelectColourInputData inputData = new SelectColourInputData("Black");

        CommonUserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Jess", "Jackson", LocalDateTime.parse("2023-11-23T12:25:42.425"), 12, "Black");

        SelectColourUserDataAccessInterface selectColourUserDataAccessInterface = new InMemoryUserDataAccessObject();

        SelectColourOutputBoundary failurePresenter = new SelectColourOutputBoundary() {
            @Override
            public void prepareSuccessView(SelectColourOutputData selectColourOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("This colour has not been unlocked yet.", error);
            }
        };

        SelectColourInputBoundary selectColourInteractor = new SelectColourInteractor(failurePresenter, user, selectColourUserDataAccessInterface);
        selectColourInteractor.execute(inputData);
    }
}