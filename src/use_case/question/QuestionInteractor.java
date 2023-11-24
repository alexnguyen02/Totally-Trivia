package use_case.question;

import java.util.ArrayList;
import java.util.Objects;

public class QuestionInteractor implements QuestionInputBoundary {

    final QuestionOutputBoundary questionPresenter;

    public QuestionInteractor(QuestionOutputBoundary questionPresenter) {

        this.questionPresenter = questionPresenter;
    }

    @Override
    public void execute(QuestionInputData questionInputData) {

        if (questionInputData.getAnswerText().equals("Cornelia")) {
            Boolean correctness = true;
            QuestionOutputData questionOutputData = new QuestionOutputData(correctness);
            questionPresenter.prepareSuccessView(questionOutputData);
        } else {
            Boolean correctness = false;
            QuestionOutputData questionOutputData = new QuestionOutputData(correctness);
            questionPresenter.prepareSuccessView(questionOutputData);
        }
    }
}
