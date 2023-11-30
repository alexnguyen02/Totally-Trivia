package interface_adaptors.game_over;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import interface_adaptors.ViewModel;

public class GameOverViewModel extends ViewModel {

    private String viewName;
    public final String TITLE_LABEL = "GAME OVER!";
    public final String POINTS_EARNED = "Points Earned: ";
    public final String CORRECT_ANSWERS = "Correct Answers: ";

    public final String PLAY_AGAIN_BUTTON_LABEL = "Play Again";
    public final String MAIN_MENU_BUTTON_LABEL = "Main Menu";
    private GameOverState state = new GameOverState();

    public GameOverViewModel() {

        super("game over");
    }
    public String getViewName() {
        return this.viewName;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state); }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void setState(GameOverState state) {
        this.state = state;
    }

    public GameOverState getState() { return this.state; }
}
