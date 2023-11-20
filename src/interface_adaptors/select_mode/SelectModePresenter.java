package interface_adaptors.select_mode;

import interface_adaptors.ViewManagerModel;
import use_case.select_mode.SelectModeOutputBoundary;
import use_case.select_mode.SelectModeOutputData;

import java.util.ArrayList;

public class SelectModePresenter implements SelectModeOutputBoundary {

    private final SelectModeViewModel selectModeViewModel;

    private final ViewManagerModel viewManagerModel;

    public SelectModePresenter(ViewManagerModel viewManagerModel, SelectModeViewModel selectModeViewModel){
        this.selectModeViewModel = selectModeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSelectModeSuccessView(SelectModeOutputData selectModeOutputData) {
        ArrayList<String> outputQuestions = selectModeOutputData.getOutputQuestions();

        SelectModeState selectModeState = new SelectModeState(outputQuestions);
        this.selectModeViewModel.setState(selectModeState);
        this.selectModeViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(this.selectModeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSelectModeFailView(String error) {
        this.selectModeViewModel.firePropertyChanged();
    }

}
