package interface_adaptors.question;

import entity.Question;

import java.util.ArrayList;

public class QuestionState {
    private Boolean questionCorrect = null;
    private int questionNum = 0;
    private Question newQuestion;

    public QuestionState(QuestionState copy) {
        questionNum = copy.questionNum;
    }
    public QuestionState() {
    }

    public Boolean getQuestionCorrect() {return questionCorrect;}

    public void setQuestionCorrect(Boolean questionCorrect) {this.questionCorrect = questionCorrect; }

    public int getQuestionNum() {return questionNum;}

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public void setNewQuestion(Question question) { this.newQuestion = question; }

    public Question getNewQuestion() { return this.newQuestion; }

}
