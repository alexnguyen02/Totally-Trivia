package interface_adaptors.game_over;

import interface_adaptors.ViewManagerModel;
import use_case.game_over.GameOverOutputBoundary;
import use_case.game_over.GameOverOutputData;

public class GameOverPresenter implements GameOverOutputBoundary {

    private ViewManagerModel viewManagerModel;

    public GameOverPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(GameOverOutputData gameOverOutputData) {

        this.viewManagerModel.setActiveView(gameOverOutputData.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
