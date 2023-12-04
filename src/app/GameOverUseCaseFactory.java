package app;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.game_over.GameOverViewModel;
import interface_adaptors.game_over.GameOverController;
import interface_adaptors.game_over.GameOverPresenter;
import use_case.game_over.GameOverInputBoundary;
import use_case.game_over.GameOverInteractor;
import use_case.game_over.GameOverOutputBoundary;
import use_case.game_over.GameOverUserDataAccessInterface;
import view.GameOverView;
import entity.User;

public class GameOverUseCaseFactory {

    private GameOverUseCaseFactory() {
    }

    public static GameOverView create(
            ViewManagerModel viewManagerModel,
            GameOverViewModel gameOverViewModel,
            GameOverUserDataAccessInterface fileUserDataAccessObject, User user) {
        GameOverController gameOverController = createGameOverController(viewManagerModel, fileUserDataAccessObject, user);
        return new GameOverView(gameOverViewModel, gameOverController);
    }

    private static GameOverController createGameOverController(ViewManagerModel viewManagerModel,
                                                            GameOverUserDataAccessInterface fileUserDataAccessObject, User user) {
        GameOverOutputBoundary gameOverOutputBoundary = new GameOverPresenter(viewManagerModel);
        GameOverInputBoundary questionInteractor = new GameOverInteractor(gameOverOutputBoundary, fileUserDataAccessObject, user);
        return new GameOverController(questionInteractor);
    }

}

