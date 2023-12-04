package interface_adaptors.select_mode;

import entity.Question;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.question.QuestionViewModel;
import use_case.select_mode.SelectModeOutputBoundary;
import use_case.select_mode.SelectModeOutputData;

import java.util.ArrayList;

public class SelectModePresenter implements SelectModeOutputBoundary {

    private final SelectModeViewModel selectModeViewModel;

    //This may be breaking CA
    private final QuestionViewModel questionViewModel;

    private final ViewManagerModel viewManagerModel;

    public SelectModePresenter(ViewManagerModel viewManagerModel, SelectModeViewModel selectModeViewModel,
                               QuestionViewModel questionViewModel){
        this.selectModeViewModel = selectModeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.questionViewModel = questionViewModel;
    }

    @Override
    public void prepareSelectModeSuccessView(SelectModeOutputData selectModeOutputData) {
        ArrayList<Question> outputQuestions = selectModeOutputData.getOutputQuestions();

        SelectModeState selectModeState = new SelectModeState(outputQuestions);
        this.selectModeViewModel.setState(selectModeState);
        this.selectModeViewModel.firePropertyChanged();

        this.questionViewModel.updateViewModel(outputQuestions.get(0));
        this.questionViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView("question");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSelectModeFailView(String error) {
        // Implement this

        this.selectModeViewModel.firePropertyChanged();
    }

}
