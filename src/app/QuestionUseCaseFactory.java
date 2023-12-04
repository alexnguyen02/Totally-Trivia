package app;


import entity.QuestionStorage;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.question.*;
import interface_adaptors.game_over.GameOverPresenter;
import interface_adaptors.game_over.GameOverViewModel;
import use_case.question.QuestionInputBoundary;
import use_case.question.QuestionInteractor;
import use_case.question.QuestionOutputBoundary;
import view.QuestionView;

public class QuestionUseCaseFactory {

    public QuestionUseCaseFactory() {
    }

    public static QuestionView create(
            ViewManagerModel viewManagerModel,
            QuestionViewModel questionViewModel, GameOverViewModel gameOverViewModel,
            QuestionStorage questionStorage) {
        QuestionController questionController = createQuestionUseCase(viewManagerModel,
                questionViewModel, questionStorage);
        CreateGameOverController createGameOverController = createGameOverController(viewManagerModel, gameOverViewModel);
        return new QuestionView(questionViewModel, questionController, createGameOverController);
    }

    private static QuestionController createQuestionUseCase(ViewManagerModel viewManagerModel,
                                                            QuestionViewModel questionViewModel,
                                                            QuestionStorage questionStorage) {
        QuestionOutputBoundary questionOutputBoundary = new QuestionPresenter(viewManagerModel, questionViewModel);
        QuestionInputBoundary questionInteractor = new QuestionInteractor(questionOutputBoundary, questionStorage);
        return new QuestionController(questionInteractor);
    }

    private static CreateGameOverController createGameOverController(ViewManagerModel viewManagerModel, GameOverViewModel gameOverViewModel) {
        CreateGameOverPresenter createGameOverPresenter = new CreateGameOverPresenter(viewManagerModel, gameOverViewModel);
        return new CreateGameOverController(createGameOverPresenter);
    }
}
