package use_case.question;

import entity.Question;

public class QuestionOutputData {

    public final boolean correctness;
    public final Question nextQuestion;
    public final int totalQuestions;
    public final String difficulty;

    public QuestionOutputData(boolean correctness, Question nextQuestion, int totalQuestions, String difficulty) {

        this.correctness = correctness;
        this.nextQuestion = nextQuestion;
        this.totalQuestions = totalQuestions;
        this.difficulty = difficulty;
    }
}
