package interface_adaptors.game_over;


import use_case.game_over.GameOverInputBoundary;
import use_case.game_over.GameOverInputData;

public class GameOverController {
    final GameOverInputBoundary gameOverInteractor;
    public GameOverController(GameOverInputBoundary gameOverInteractor) {
        this.gameOverInteractor = gameOverInteractor;
    }

    public void execute(String viewName, Integer points) {
        GameOverInputData gameOverInputData = new GameOverInputData(viewName, points);
        gameOverInteractor.execute(gameOverInputData);
    }
}
