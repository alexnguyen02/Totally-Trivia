package src.use_case.select_mode;

import src.data_access.InMemorySelectModeAccessObject;
import src.entity.Question;

import java.util.ArrayList;

public class SelectModeInteractor implements SelectModeInputBoundary {

    private SelectModeDataObjectInterface selectModeDataAccessObject;

    private SelectModeOutputBoundary selectModePresenter;

    public SelectModeInteractor(SelectModeDataObjectInterface selectModeDataAccessObject, SelectModeOutputBoundary selectModePresenter){
        this.selectModeDataAccessObject = selectModeDataAccessObject;
        this.selectModePresenter = selectModePresenter;
    }

    @Override
    public void execute(SelectModeInputData selectModeInputData) {
        String category = selectModeInputData.getCategory();
        String difficultyLevel = selectModeInputData.getDifficultyLevel();
        int numOfQuestions = selectModeInputData.getNumberOfQuestions();

        ArrayList<Question> listOfQuestions = selectModeDataAccessObject.getQuestions(category, difficultyLevel, numOfQuestions);
        SelectModeOutputData outputData = new SelectModeOutputData(listOfQuestions);
        selectModePresenter.prepareSelectModeSuccessView(outputData);

        /// Consider cases of fail view
    }
}
