package interface_adaptors.select_colour;

import java.awt.*;
import java.util.List;
import interface_adaptors.ViewManagerModel;
import use_case.select_colour.SelectColourOutputBoundary;
import use_case.select_colour.SelectColourInputData;
import use_case.select_colour.SelectColourOutputBoundary;
import use_case.select_colour.SelectColourOutputData;

import javax.swing.*;


public class SelectColourPresenter implements SelectColourOutputBoundary{
    private final SelectColourViewModel selectColourViewModel;
    private ViewManagerModel viewManagerModel;

    public SelectColourPresenter(ViewManagerModel viewManagerModel, SelectColourViewModel selectColourViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.selectColourViewModel = selectColourViewModel;
    }

    @Override
    public void prepareSuccessView(SelectColourOutputData selectColourOutputData) {
        String colour = selectColourOutputData.getColour();

        SelectColourState selectColourState = new SelectColourState(colour);
        this.selectColourViewModel.setState(selectColourState);
        selectColourViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(selectColourViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SelectColourState selectColourState = selectColourViewModel.getState();
        selectColourState.setColourError(error);
        selectColourViewModel.firePropertyChanged();
    }
}
