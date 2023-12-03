package use_case.game_over;

import entity.User;

public class GameOverInteractor implements GameOverInputBoundary {

    final GameOverUserDataAccessInterface userDataAccessObject;
    final GameOverOutputBoundary gameOverPresenter;
    final User logged_in;

    public GameOverInteractor(GameOverOutputBoundary gameOverOutputBoundary,
                              GameOverUserDataAccessInterface gameOverDataAccessInterface, User logged_in) {
        this.userDataAccessObject = gameOverDataAccessInterface;
        this.gameOverPresenter = gameOverOutputBoundary;
        this.logged_in = logged_in;
    }

    @Override
    public void execute(GameOverInputData gameOverInputData) {
        String username = logged_in.getName();
        userDataAccessObject.changePoints(username, gameOverInputData.getPoints());
        logged_in.setPoints(logged_in.getPoints() + gameOverInputData.getPoints());
        GameOverOutputData gameOverOutputData = new GameOverOutputData(gameOverInputData.getViewName());
        gameOverPresenter.prepareSuccessView(gameOverOutputData);
    }
}
