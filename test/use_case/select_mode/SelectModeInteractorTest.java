package use_case.select_mode;

import data_access.InMemorySelectModeAccessObject;
import data_access.QuestionStorageDataAccessObject;
import entity.CommonQuestionStorage;
import entity.Question;
import entity.QuestionStorage;
import use_case.select_mode.*;

import java.util.ArrayList;
import java.util.List;
import entity.Question;
import entity.QuestionStorage;

import java.util.ArrayList;

import static org.junit.Assert.*;

/// Test case for SelectModeInteractor

public class SelectModeInteractorTest {

    @org.junit.Test
    public void successTest(){
        SelectModeInputData inputData = new SelectModeInputData("Animals", "Easy", "2");
        SelectModeDataObjectInterface selectModeDatabase = new InMemorySelectModeAccessObject();
        QuestionStorage questionStorage = new CommonQuestionStorage();
        QuestionStorage questionStorageDataAccessObject = new QuestionStorage() {
            @Override
            public ArrayList<Question> getQuestions() {
                return null;
            }

            @Override
            public void setQuestions(ArrayList<Question> newQuestions) {

            }
        };

        SelectModeOutputBoundary successPresenter = new SelectModeOutputBoundary() {
            @Override
            public void prepareSelectModeSuccessView(SelectModeOutputData selectModeOutputData) {
                ArrayList<Question> actualQuestions = selectModeOutputData.getOutputQuestions();
                for (Question q: actualQuestions){
                    assertEquals("Dog or Cat?", q.getContent());
                    assertEquals("Animals", q.getCategory());
                    assertEquals("Easy", q.getDifficultyLevel());
                }
                assertEquals(2, actualQuestions.size());
            }

            @Override
            public void prepareSelectModeFailView(String error) {
                fail("Select mode use case failure is unexpected");
            }
        };
        SelectModeInputBoundary selectModeInputInteractor = new SelectModeInteractor(selectModeDatabase, successPresenter, questionStorage);
        SelectModeInputBoundary selectModeInputInteractor = new SelectModeInteractor(selectModeDatabase, successPresenter, questionStorageDataAccessObject);
        selectModeInputInteractor.execute(inputData);
    }
}