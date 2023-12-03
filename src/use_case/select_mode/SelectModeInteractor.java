package use_case.select_mode;

import entity.Question;
import use_case.QuestionStorageDataAccessInterface;

import java.util.ArrayList;

public class SelectModeInteractor implements SelectModeInputBoundary {

    private SelectModeDataObjectInterface selectModeDataAccessObject;
    private QuestionStorageDataAccessInterface questionStorageDataAccessObject;

    private SelectModeOutputBoundary selectModePresenter;

    public SelectModeInteractor(SelectModeDataObjectInterface selectModeDataAccessObject,
                                SelectModeOutputBoundary selectModePresenter,
                                QuestionStorageDataAccessInterface questionStorageDataAccessObject){
        this.selectModeDataAccessObject = selectModeDataAccessObject;
        this.selectModePresenter = selectModePresenter;
        this.questionStorageDataAccessObject = questionStorageDataAccessObject;
    }

    @Override
    public void execute(SelectModeInputData selectModeInputData) {
        String category = selectModeInputData.getCategory();
        String difficultyLevel = selectModeInputData.getDifficultyLevel();
        int numOfQuestions = selectModeInputData.getNumberOfQuestions();

        ArrayList<Question> listOfQuestions = selectModeDataAccessObject.getQuestions(category, difficultyLevel, numOfQuestions);

        // For testing purpose
        System.out.println(listOfQuestions);


        questionStorageDataAccessObject.setQuestions(listOfQuestions);
        SelectModeOutputData outputData = new SelectModeOutputData(listOfQuestions);
        selectModePresenter.prepareSelectModeSuccessView(outputData);

        /// Consider cases of fail view
    }
}
