package test;

import data_access.InMemorySelectModeAccessObject;
import data_access.QuestionStorageDataAccessObject;
import entity.CommonUserFactory;
import entity.Question;
import entity.User;
import use_case.select_colour.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SelectColourInteractorTest {
    @org.junit.Test
    public void successTest(){
        SelectColourInputData inputData = new SelectColourInputData("Orange");

        CommonUserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Rob", "Johnson", LocalDateTime.parse("2023-12-01T14:58:50.150"), 42, "Orange");

    }
}
