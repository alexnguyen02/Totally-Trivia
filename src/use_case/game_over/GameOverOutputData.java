package use_case.game_over;

public class GameOverOutputData {

    final String viewName;

    public GameOverOutputData(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() { return this.viewName; }
}
