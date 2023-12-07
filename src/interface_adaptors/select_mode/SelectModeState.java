package interface_adaptors.select_mode;

import entity.Question;

import java.util.ArrayList;

public class SelectModeState {
    private String category = null;
    private String difficultyLevel = null;
    private String numOfQuestions = null;

    private String notSelectedError = null;

    private ArrayList<Question> outputQuestions;

    public SelectModeState(ArrayList<Question> outputQuestions){
        this.outputQuestions = outputQuestions;
    }

    public ArrayList<Question> getOutputQuestions() {
        return this.outputQuestions;
    }

    public void setOutputQuestions(ArrayList<Question> outputQuestions) {
        this.outputQuestions = outputQuestions;
    }

    public String getCategory(){
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficultyLevel(){
        return this.difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel){
        this.difficultyLevel = difficultyLevel;
    }

    public String getNumOfQuestionsString(){
        return this.numOfQuestions;
    }

    public void setNumOfQuestions(String numOfQuestions){
        this.numOfQuestions = numOfQuestions;
    }

    public String toString() {
        StringBuilder questionsToString = new StringBuilder();

        for (Question q : this.outputQuestions){
            questionsToString.append(q).append("\n");
        }

        return questionsToString.toString();
    }

    public void setNotSelectedError(String notSelectedError){
        this.notSelectedError = notSelectedError;
    }

    public String getNotSelectedError(){
        return this.notSelectedError;
    }
}
