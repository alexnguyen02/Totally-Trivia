package src.interface_adaptors.select_mode;

import src.interface_adaptors.ViewManagerModel;
import src.use_case.select_mode.SelectModeOutputBoundary;
import src.use_case.select_mode.SelectModeOutputData;

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
        ArrayList<String> outputQuestions = selectModeOutputData.getAllQuestions();

        SelectModeState selectModeState = new SelectModeState(outputQuestions);
        this.selectModeViewModel.setState(selectModeState);
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSelectModeFailView(String error) {
        this.selectModeViewModel.firePropertyChanged();
    }

}
