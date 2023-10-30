package src.use_case;

import java.util.ArrayList;

public class Answers {
    // A class represents all the possible answers (and including the correct answer to ONE QUESTION in trivia cash.

    private ArrayList<String> possibleAnswers;

    private String correctAnswer;

    public Answers(ArrayList<String> possibleAnswers, String correctAnswer) {
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkCorrectAnswer(String userAnswer){
        return userAnswer.equals(this.correctAnswer);
    }
}
