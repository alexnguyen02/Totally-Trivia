package use_case.game_over;

import use_case.logout.LogoutOutputData;

public interface GameOverOutputBoundary {
    void prepareSuccessView(GameOverOutputData gameOverOutputData);

}
