package interface_adaptors.question;

import use_case.question.QuestionInputBoundary;
import use_case.question.QuestionInputData;

public class QuestionController {

    final QuestionInputBoundary questionUseCaseInteractor;

    public QuestionController(QuestionInputBoundary questionUseCaseInteractor) {
        this.questionUseCaseInteractor = questionUseCaseInteractor;
    }

    public void execute(String answerText, int questionNum) {
        QuestionInputData questionInputData = new QuestionInputData(answerText, questionNum);

        questionUseCaseInteractor.execute(questionInputData);
    }
}
