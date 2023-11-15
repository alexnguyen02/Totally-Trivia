package use_case.select_mode;

import src.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class SelectModeOutputData {
    private ArrayList<Question> listOfQuestions;

    public SelectModeOutputData(ArrayList<Question> listOfQuestions){

        this.listOfQuestions = listOfQuestions;
    }

    public ArrayList<String> getAllQuestions(){
        ArrayList<String> allQuestions = new ArrayList<>();

        for (Question q : listOfQuestions){
            String question = q.getContent();
            allQuestions.add(question);
        }
        return allQuestions;
    }

}
