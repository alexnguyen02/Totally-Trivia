package interface_adaptors.question;

import interface_adaptors.ViewManagerModel;
import use_case.question.QuestionOutputBoundary;
import use_case.question.QuestionOutputData;

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
        questionState.setQuestionNum(questionState.getQuestionNum() + 1);
        questionState.setNewQuestion(questionOutputData.nextQuestion);

        questionViewModel.setState(questionState);
        questionViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        questionViewModel.firePropertyChanged();
    }
}
