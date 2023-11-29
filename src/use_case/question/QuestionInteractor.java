package use_case.question;

import data_access.QuestionStorageDataAccessObject;
import entity.Question;

import java.util.ArrayList;
import java.util.Objects;

public class QuestionInteractor implements QuestionInputBoundary {

    final QuestionOutputBoundary questionPresenter;
    final QuestionStorageDataAccessObject questionStorageDataAccessObject;

    public QuestionInteractor(QuestionOutputBoundary questionPresenter,
                              QuestionStorageDataAccessObject questionStorageDataAccessObject) {

        this.questionPresenter = questionPresenter;
        this.questionStorageDataAccessObject = questionStorageDataAccessObject;
    }

    @Override
    public void execute(QuestionInputData questionInputData) {

        ArrayList<Question> questions = questionStorageDataAccessObject.getQuestions();
        System.out.println(questions);
        Question this_question = questions.get(questionInputData.getQuestionNum());

        if (questionInputData.getAnswerText().equals(this_question.getAnswerPackage().getCorrectAnswer())) {
            Boolean correctness = true;
            QuestionOutputData questionOutputData = new QuestionOutputData(correctness, questions.get(questionInputData.getQuestionNum() + 1));
            questionPresenter.prepareSuccessView(questionOutputData);
        } else {
            Boolean correctness = false;
            QuestionOutputData questionOutputData = new QuestionOutputData(correctness, questions.get(questionInputData.getQuestionNum() + 1));
            questionPresenter.prepareSuccessView(questionOutputData);
        }
    }
}
