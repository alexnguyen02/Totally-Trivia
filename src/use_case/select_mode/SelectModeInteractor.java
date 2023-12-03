package use_case.select_mode;

import data_access.QuestionStorageDataAccessObject;
import entity.Question;
import entity.QuestionStorage;

import java.util.ArrayList;

public class SelectModeInteractor implements SelectModeInputBoundary {

    private SelectModeDataObjectInterface selectModeDataAccessObject;
    private QuestionStorage questionStorage;

    private SelectModeOutputBoundary selectModePresenter;

    public SelectModeInteractor(SelectModeDataObjectInterface selectModeDataAccessObject,
                                SelectModeOutputBoundary selectModePresenter,
                                QuestionStorage questionStorage){
        this.selectModeDataAccessObject = selectModeDataAccessObject;
        this.selectModePresenter = selectModePresenter;
        this.questionStorage = questionStorage;
    }

    @Override
    public void execute(SelectModeInputData selectModeInputData) {
        String category = selectModeInputData.getCategory();
        String difficultyLevel = selectModeInputData.getDifficultyLevel();
        int numOfQuestions = selectModeInputData.getNumberOfQuestions();

        ArrayList<Question> listOfQuestions = selectModeDataAccessObject.getQuestions(category, difficultyLevel, numOfQuestions);

        // For testing purpose
        System.out.println(listOfQuestions);


        questionStorage.setQuestions(listOfQuestions);
        SelectModeOutputData outputData = new SelectModeOutputData(listOfQuestions);
        selectModePresenter.prepareSelectModeSuccessView(outputData);

        /// Consider cases of fail view
    }
}
