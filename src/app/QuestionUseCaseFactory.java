package app;


import data_access.QuestionStorageDataAccessObject;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.question.*;
import interface_adaptors.game_over.GameOverPresenter;
import interface_adaptors.game_over.GameOverViewModel;
import use_case.question.QuestionInputBoundary;
import use_case.question.QuestionInteractor;
import use_case.question.QuestionOutputBoundary;
import view.QuestionView;

public class QuestionUseCaseFactory {

    private QuestionUseCaseFactory() {
    }

    public static QuestionView create(
            ViewManagerModel viewManagerModel,
            QuestionViewModel questionViewModel, GameOverViewModel gameOverViewModel,
            QuestionStorageDataAccessObject questionStorageDataAccessObject) {
        QuestionController questionController = createQuestionUseCase(viewManagerModel,
                questionViewModel, questionStorageDataAccessObject);
        CreateGameOverController createGameOverController = createGameOverController(viewManagerModel, gameOverViewModel);
        return new QuestionView(questionViewModel, questionController, createGameOverController);
    }

    private static QuestionController createQuestionUseCase(ViewManagerModel viewManagerModel,
                                                            QuestionViewModel questionViewModel,
                                                            QuestionStorageDataAccessObject questionStorageDataAccessObject) {
        QuestionOutputBoundary questionOutputBoundary = new QuestionPresenter(viewManagerModel, questionViewModel);
        QuestionInputBoundary questionInteractor = new QuestionInteractor(questionOutputBoundary, questionStorageDataAccessObject);
        return new QuestionController(questionInteractor);
    }

    private static CreateGameOverController createGameOverController(ViewManagerModel viewManagerModel, GameOverViewModel gameOverViewModel) {
        CreateGameOverPresenter createGameOverPresenter = new CreateGameOverPresenter(viewManagerModel, gameOverViewModel);
        return new CreateGameOverController(createGameOverPresenter);
    }
}