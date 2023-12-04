package interface_adaptors.game_over;

public class GameOverState {

    private int totalNum;
    private int correctNum;
    private int incorrectNum;
    private int pointsEarned;

    public GameOverState(int totalNum, int CorrectNum, int incorrectNum, int pointsEarned) {
        this.totalNum = totalNum;
        this.correctNum = correctNum;
        this.incorrectNum = incorrectNum;
        this.pointsEarned = pointsEarned;
    }

    public GameOverState() {}

    public int getCorrectNum() { return correctNum; }

    public int getIncorrectNum() { return incorrectNum; }

    public int getTotalNum() { return totalNum; }

    public int getPointsEarned() { return pointsEarned; }

    public void setCorrectNum(int correctNum) { this.correctNum = correctNum; }

    public void setIncorrectNum(int incorrectNum) { this.incorrectNum = incorrectNum; }

    public void setPointsEarned(int pointsEarned) { this.pointsEarned = pointsEarned; }

    public void setTotalNum(int totalNum) { this.totalNum = totalNum; }
}