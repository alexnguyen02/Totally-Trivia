package interface_adaptors.game_over;


public class GameOverController {
    final GameOverPresenter gameOverPresenter;
    public GameOverController(GameOverPresenter gameOverPresenter) {
        this.gameOverPresenter = gameOverPresenter;
    }

    public void execute(String viewName) {
        gameOverPresenter.prepareSuccessView(viewName);
    }
}
