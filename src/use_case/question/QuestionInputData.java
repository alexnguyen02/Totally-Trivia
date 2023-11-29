package use_case.question;

public class QuestionInputData {

    final private String answerText;
    final private int questionNum;

    public QuestionInputData(String answerText, int questionNum) {

        this.answerText = answerText;
        this.questionNum = questionNum;
    }

    public String getAnswerText() { return answerText; }

    public int getQuestionNum() { return questionNum; }
}
