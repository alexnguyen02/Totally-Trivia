package app;


import interface_adaptors.ViewManagerModel;
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
            QuestionViewModel questionViewModel) {
        QuestionController questionController = createQuestionUseCase(viewManagerModel, questionViewModel);
        return new QuestionView(questionViewModel, questionController);
    }

    private static QuestionController createQuestionUseCase(ViewManagerModel viewManagerModel,
                                                            QuestionViewModel questionViewModel) {
        QuestionOutputBoundary questionOutputBoundary = new QuestionPresenter(viewManagerModel, questionViewModel);
        QuestionInputBoundary questionInteractor = new QuestionInteractor(questionOutputBoundary);
        return new QuestionController(questionInteractor);
    }
}