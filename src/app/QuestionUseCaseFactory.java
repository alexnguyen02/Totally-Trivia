package app;


import data_access.QuestionStorageDataAccessObject;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.game_over.GameOverViewModel;
import interface_adaptors.question.QuestionPresenter;
import interface_adaptors.question.QuestionViewModel;
import use_case.question.QuestionInputBoundary;
import use_case.question.QuestionInteractor;
import use_case.question.QuestionOutputBoundary;
import view.QuestionView;
import interface_adaptors.question.QuestionController;
import view.SelectModeView;

import javax.swing.*;
import java.io.IOException;

public class QuestionUseCaseFactory {

    private QuestionUseCaseFactory() {
    }

    public static QuestionView create(
            ViewManagerModel viewManagerModel,
            QuestionViewModel questionViewModel, GameOverViewModel gameOverViewModel,
            QuestionStorageDataAccessObject questionStorageDataAccessObject) {
        QuestionController questionController = createQuestionUseCase(viewManagerModel,
                questionViewModel, questionStorageDataAccessObject);
        return new QuestionView(questionViewModel, questionController, gameOverViewModel);
    }

    private static QuestionController createQuestionUseCase(ViewManagerModel viewManagerModel,
                                                            QuestionViewModel questionViewModel,
                                                            QuestionStorageDataAccessObject questionStorageDataAccessObject) {
        QuestionOutputBoundary questionOutputBoundary = new QuestionPresenter(viewManagerModel, questionViewModel);
        QuestionInputBoundary questionInteractor = new QuestionInteractor(questionOutputBoundary, questionStorageDataAccessObject);
        return new QuestionController(questionInteractor);
    }
}