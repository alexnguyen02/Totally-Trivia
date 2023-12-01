package app;

import view.SelectColourView;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.select_colour.SelectColourViewModel;
import use_case.select_colour.SelectColourDataAccessInterface;
import interface_adaptors.select_colour.SelectColourController;
import interface_adaptors.select_colour.SelectColourViewModel;
import use_case.select_colour.SelectColourOutputBoundary;
import use_case.select_colour.SelectColourInputBoundary;
import interface_adaptors.select_colour.SelectColourPresenter;
import use_case.select_colour.SelectColourInteractor;

import javax.swing.*;
import java.io.IOException;

public class SelectColourUseCaseFactory {
    private SelectColourUseCaseFactory() {}

    public static SelectColourView create(
            ViewManagerModel viewManagerModel, SelectColourViewModel selectColourViewModel, SelectColourDataAccessInterface selectColourDataAccessObject) {

        try {
            SelectColourController selectColourController = createSelectColourController(viewManagerModel, selectColourViewModel, selectColourDataAccessObject);
            return new SelectColourView(selectColourViewModel, selectColourController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open file.");
        }

        return null;
    }

    private static SelectColourController createSelectColourController(ViewManagerModel viewManagerModel, SelectColourViewModel selectColourViewModel, SelectColourDataAccessInterface selectColourDataAccessObject) throws IOException {

        SelectColourOutputBoundary selectColourOutputBoundary = new SelectColourPresenter(viewManagerModel, selectColourViewModel);


        SelectColourInputBoundary selectColourInteractor = new SelectColourInteractor(selectColourDataAccessObject, selectColourOutputBoundary);

        return new SelectColourController(selectColourInteractor);
    }
}
