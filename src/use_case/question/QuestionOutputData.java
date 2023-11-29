package use_case.question;

import entity.Question;

import java.util.ArrayList;

public class QuestionOutputData {

    public final boolean correctness;
    public final Question nextQuestion;

    public QuestionOutputData(boolean correctness, Question nextQuestion) {

        this.correctness = correctness;
        this.nextQuestion = nextQuestion;
    }
}
