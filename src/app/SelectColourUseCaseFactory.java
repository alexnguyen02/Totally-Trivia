package app;

import data_access.FileUserDataAccessObject;
import view.*;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.select_colour.SelectColourViewModel;
import interface_adaptors.select_colour.SelectColourController;
import use_case.select_colour.SelectColourOutputBoundary;
import use_case.select_colour.SelectColourInputBoundary;
import interface_adaptors.select_colour.SelectColourPresenter;
import use_case.select_colour.SelectColourInteractor;
import entity.User;

import javax.swing.*;
import java.io.IOException;

public class SelectColourUseCaseFactory {
    private SelectColourUseCaseFactory() {}

    public static SelectColourView create(
            ViewManagerModel viewManagerModel, SelectColourViewModel selectColourViewModel, User user, FileUserDataAccessObject fileUserDataAccessObject, AccountView accountView, MainScreenView mainScreenView, QuestionView questionView, SelectModeView selectModeView, GameOverView gameOverView) {

        try {
            SelectColourController selectColourController = createSelectColourController(viewManagerModel, selectColourViewModel, user, fileUserDataAccessObject);
            return new SelectColourView(selectColourViewModel, selectColourController, viewManagerModel, accountView, mainScreenView, questionView, selectModeView, gameOverView);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open file.");
        }

        return null;
    }

    private static SelectColourController createSelectColourController(ViewManagerModel viewManagerModel, SelectColourViewModel selectColourViewModel, User user, FileUserDataAccessObject fileUserDataAccessObject) throws IOException {

        SelectColourOutputBoundary selectColourOutputBoundary = new SelectColourPresenter(viewManagerModel, selectColourViewModel);


        SelectColourInputBoundary selectColourInteractor = new SelectColourInteractor(selectColourOutputBoundary, user, fileUserDataAccessObject);

        return new SelectColourController(selectColourInteractor);
    }
}
