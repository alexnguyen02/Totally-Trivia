package use_case.select_mode;

import data_access.SelectModeDataAccessObject;
import entity.CommonQuestionStorage;
import entity.Question;
import entity.QuestionStorage;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SelectModeIntegrationTest {
    SelectModeInputData inputData;
    SelectModeDataObjectInterface selectModeDataAccessObject;
    QuestionStorage questionStorage;
    SelectModeOutputBoundary successPresenter;


    @Before
    public void init(){
       inputData = new SelectModeInputData("Film", "Hard", "10");
       selectModeDataAccessObject = new SelectModeDataAccessObject();
       questionStorage = new CommonQuestionStorage();
    }

    @org.junit.Test
    public void successTest(){
        successPresenter = new SelectModeOutputBoundary() {
            @Override
            public void prepareSelectModeSuccessView(SelectModeOutputData selectModeOutputData) {
                ArrayList<Question> outputQuestions = selectModeOutputData.getOutputQuestions();
                assertEquals(10, outputQuestions.size());

                for (Question q: outputQuestions){
                    int answerPackageSize = q.getAnswerPackage().getPossibleAnswers().size();
                    assertEquals("Entertainment: Film", q.getCategory());
                    assertEquals("Hard", q.getDifficultyLevel());
                }
            }
            @Override
            public void prepareSelectModeFailView(String error) {

                fail("Select mode use case failure is unexpected");
            }
        };
        SelectModeInputBoundary selectModeInputInteractor =
                new SelectModeInteractor(selectModeDataAccessObject, successPresenter, questionStorage);
        selectModeInputInteractor.execute(inputData);
    }
}
