package use_case.select_mode;

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
        String numOfQuestions = selectModeInputData.getNumberOfQuestionsString();

        if (category.equals("Select") || difficultyLevel.equals("Select") || numOfQuestions.equals("Select")){
            selectModePresenter.prepareSelectModeFailView(
                    "Please select a category, a difficulty level and a number of questions");
        } else {
            ArrayList<Question> listOfQuestions = selectModeDataAccessObject.getQuestions(category, difficultyLevel, numOfQuestions);

            // For testing purpose
            System.out.println(listOfQuestions);

            questionStorage.setQuestions(listOfQuestions);
            SelectModeOutputData outputData = new SelectModeOutputData(listOfQuestions);
            selectModePresenter.prepareSelectModeSuccessView(outputData);
        }
    }
}
