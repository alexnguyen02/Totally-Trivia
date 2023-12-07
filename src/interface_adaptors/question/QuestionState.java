package interface_adaptors.question;

import entity.Question;

public class QuestionState {
    private Boolean questionCorrect = null;
    private int questionNum;
    private int totalQuestions;
    private Question newQuestion;
    private int totalPoints;
    private int questionsCorrect;

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
    public int getTotalQuestions() { return this.totalQuestions; }

    public void setTotalQuestions(int newTotal) { this.totalQuestions = newTotal; }

    public void setQuestionsCorrect(int questionsCorrect) {
        this.questionsCorrect = questionsCorrect;
    }

    public int getQuestionsCorrect() {
        return questionsCorrect;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
