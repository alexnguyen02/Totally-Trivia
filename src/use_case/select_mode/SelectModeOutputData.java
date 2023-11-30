package use_case.select_mode;

import entity.Question;

import java.util.ArrayList;
import java.util.List;

public class SelectModeOutputData {
    private ArrayList<Question> listOfQuestions;

    public SelectModeOutputData(ArrayList<Question> listOfQuestions){
        this.listOfQuestions = listOfQuestions;
    }

    public ArrayList<Question> getOutputQuestions(){
        return this.listOfQuestions;
    }
}
