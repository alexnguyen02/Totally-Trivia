package interface_adaptors.question;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.game_over.GameOverState;
import interface_adaptors.game_over.GameOverViewModel;

public class CreateGameOverPresenter {

    private ViewManagerModel viewManagerModel;
    private final GameOverViewModel gameOverViewModel;

    public CreateGameOverPresenter(ViewManagerModel viewManagerModel, GameOverViewModel gameOverViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameOverViewModel = gameOverViewModel;
    }

    public void prepareSuccessView(QuestionState inputState) {
        GameOverState currentState = gameOverViewModel.getState();
        currentState.setPointsEarned(inputState.getTotalPoints());
        currentState.setCorrectNum(inputState.getQuestionsCorrect());
        currentState.setTotalNum(inputState.getTotalQuestions());
        gameOverViewModel.setState(currentState);

        this.gameOverViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView("game over");
        this.viewManagerModel.firePropertyChanged();
    }
}
