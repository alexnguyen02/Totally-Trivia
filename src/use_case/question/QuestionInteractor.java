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
        Question this_question = questions.get(questionInputData.getQuestionNum());
        Boolean correctness = questionInputData.getAnswerText().equals(this_question.getAnswerPackage().getCorrectAnswer());
        if (questionInputData.getQuestionNum() < questions.size() - 1) {
            QuestionOutputData questionOutputData = new QuestionOutputData(correctness,
                    questions.get(questionInputData.getQuestionNum() + 1), questions.size(), this_question.getDifficultyLevel());
            questionPresenter.prepareSuccessView(questionOutputData);
        } else {
            QuestionOutputData questionOutputData = new QuestionOutputData(correctness,
                    null, questions.size(), this_question.getDifficultyLevel());
            questionStorageDataAccessObject.setQuestions(new ArrayList<>());
            questionPresenter.prepareSuccessView(questionOutputData);
        }
    }
}
