package entity;

import java.util.ArrayList;

public class AnswerPackage {
    /// A class represents all the possible answers (including the correct answer)
    // to ONE QUESTION in the trivia cash game

    private ArrayList<String> possibleAnswers;

    private String correctAnswer;

    public AnswerPackage(ArrayList<String> possibleAnswers, String correctAnswer) {
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkCorrectAnswer(String userAnswer){
        return userAnswer.equals(this.correctAnswer);
    }

    public ArrayList<String> getPossibleAnswers(){
        return this.possibleAnswers;
    }

    public String getCorrectAnswer(){
        return this.correctAnswer;
    }
}
