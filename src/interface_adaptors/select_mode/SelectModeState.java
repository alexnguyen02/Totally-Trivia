package interface_adaptors.select_mode;

import entity.Question;

import java.util.ArrayList;

public class SelectModeState {
    private String category = "";
    private String difficultyLevel = "";
    private Integer numOfQuestions = 0;

    private ArrayList<Question> outputQuestions;

    public SelectModeState(SelectModeState copy){
        this.category = copy.category;
        this.difficultyLevel = copy.difficultyLevel;
        this.numOfQuestions = copy.numOfQuestions;
    }

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

    public Integer getNumOfQuestions(){
        return this.numOfQuestions;
    }

    public void setNumOfQuestions(Integer numOfQuestions){
        this.numOfQuestions = numOfQuestions;
    }

    public String toString() {
        StringBuilder questionsToString = new StringBuilder();

        for (Question q : this.outputQuestions){
            questionsToString.append(q).append("\n");
        }

        return questionsToString.toString();
    }

}
