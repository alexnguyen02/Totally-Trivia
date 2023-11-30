package interface_adaptors.question;

import interface_adaptors.ViewManagerModel;
import use_case.question.QuestionOutputBoundary;
import use_case.question.QuestionOutputData;

import java.util.Objects;

public class QuestionPresenter implements QuestionOutputBoundary {

    private final QuestionViewModel questionViewModel;
    private ViewManagerModel viewManagerModel;

    public QuestionPresenter(ViewManagerModel viewManagerModel,
                          QuestionViewModel questionViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.questionViewModel = questionViewModel;
    }
    public void prepareSuccessView(QuestionOutputData questionOutputData) {
        QuestionState questionState = questionViewModel.getState();
        questionState.setQuestionCorrect(questionOutputData.correctness);
        questionState.setNewQuestion(questionOutputData.nextQuestion);
        questionState.setTotalQuestions(questionOutputData.totalQuestions);
        if (questionOutputData.correctness) {
            if (questionOutputData.difficulty.equals("easy")) {
                questionState.setTotalPoints(questionState.getTotalPoints() + 1);
            } else if (questionOutputData.difficulty.equals("medium")) {
                questionState.setTotalPoints(questionState.getTotalPoints() + 2);
            } else if (questionOutputData.difficulty.equals("hard")) {
                questionState.setTotalPoints(questionState.getTotalPoints() + 3);
            }
            if (questionState.getNewQuestion() != null) {
                questionViewModel.updateViewModel(questionOutputData.nextQuestion);
            }
        }

        questionViewModel.setState(questionState);
        questionViewModel.firePropertyChanged();

        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        questionViewModel.firePropertyChanged();
    }
}
