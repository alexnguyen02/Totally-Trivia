package interface_adaptors.question;

import java.util.ArrayList;

public class QuestionState {
    private Boolean questionCorrect = null;
    private int questionNum = 0;

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

}
