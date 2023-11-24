package use_case.question;

public class QuestionInputData {

    final private String answerText;

    public QuestionInputData(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return answerText;
    }
}
