package interface_adaptors.game_over;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.ViewModel;
import interface_adaptors.select_mode.SelectModeViewModel;
import interface_adaptors.game_over.GameOverViewModel;

public class GameOverPresenter {

    private ViewManagerModel viewManagerModel;

    public GameOverPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(String viewName) {

        this.viewManagerModel.setActiveView(viewName);
        this.viewManagerModel.firePropertyChanged();
    }
}
