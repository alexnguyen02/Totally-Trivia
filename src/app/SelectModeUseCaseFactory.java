package app;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.select_mode.SelectModeController;
import interface_adaptors.select_mode.SelectModePresenter;
import interface_adaptors.select_mode.SelectModeViewModel;
import use_case.select_mode.SelectModeDataObjectInterface;
import use_case.select_mode.SelectModeInputBoundary;
import use_case.select_mode.SelectModeInteractor;
import use_case.select_mode.SelectModeOutputBoundary;
import view.SelectModeView;

import javax.swing.*;
import java.io.IOException;

public class SelectModeUseCaseFactory {
    private SelectModeUseCaseFactory(){
    }

    public static SelectModeView create(
            ViewManagerModel viewManagerModel,
            SelectModeViewModel selectModeViewModel,
            SelectModeDataObjectInterface selectModeDataObject) {

        try {
            SelectModeController selectModeController = createSelectModeUseCase(viewManagerModel, selectModeViewModel, selectModeDataObject);
            return new SelectModeView(selectModeViewModel, selectModeController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not access data object");
        }

        return null;
    }

    private static SelectModeController createSelectModeUseCase(
            ViewManagerModel viewManagerModel,
            SelectModeViewModel selectModeViewModel,
            SelectModeDataObjectInterface selectModeDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SelectModeOutputBoundary selectModeOutputBoundary = new SelectModePresenter(viewManagerModel, selectModeViewModel);

        SelectModeInputBoundary selectModeInteractor= new SelectModeInteractor(selectModeDataAccessObject, selectModeOutputBoundary);

        return new SelectModeController(selectModeInteractor);
    }
}
