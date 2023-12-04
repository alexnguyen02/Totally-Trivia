package use_case.question;

import entity.Question;
import entity.QuestionStorage;

import java.util.ArrayList;
import java.util.Objects;

public class QuestionInteractor implements QuestionInputBoundary {

    final QuestionOutputBoundary questionPresenter;
    final QuestionStorage questionStorage;

    public QuestionInteractor(QuestionOutputBoundary questionPresenter,
                              QuestionStorage questionStorage) {

        this.questionPresenter = questionPresenter;
        this.questionStorage = questionStorage;
    }

    @Override
    public void execute(QuestionInputData questionInputData) {

        ArrayList<Question> questions = questionStorage.getQuestions();
        Question this_question = questions.get(questionInputData.getQuestionNum());
        Boolean correctness = questionInputData.getAnswerText().equals(this_question.getAnswerPackage().getCorrectAnswer());
        if (questionInputData.getQuestionNum() < questions.size() - 1) {
            QuestionOutputData questionOutputData = new QuestionOutputData(correctness,
                    questions.get(questionInputData.getQuestionNum() + 1), questions.size(), this_question.getDifficultyLevel());
            questionPresenter.prepareSuccessView(questionOutputData);
        } else {
            QuestionOutputData questionOutputData = new QuestionOutputData(correctness,
                    null, questions.size(), this_question.getDifficultyLevel());
            questionStorage.setQuestions(new ArrayList<>());
            questionPresenter.prepareSuccessView(questionOutputData);
        }
    }
}
