package use_case.game_over;

public class GameOverInputData {

    final private Integer points;
    final private String viewName;

    public GameOverInputData(String viewName, Integer points) {
        this.points = points;
        this.viewName = viewName;
    }

    public Integer getPoints() {
        return points;
    }
    public String getViewName() { return viewName; }
}
