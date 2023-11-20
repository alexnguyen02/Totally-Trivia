package use_case.select_mode;

import entity.Question;

import java.util.ArrayList;
import java.util.List;

public class SelectModeOutputData {
    private ArrayList<Question> listOfQuestions;

    public SelectModeOutputData(ArrayList<Question> listOfQuestions){
        this.listOfQuestions = listOfQuestions;
    }

    public ArrayList<String> getOutputQuestions(){
        ArrayList<String> allQuestions = new ArrayList<>();

        for (Question q : this.listOfQuestions){
            System.out.println(q);
            String question = q.getContent();
            allQuestions.add(question);
        }
        return allQuestions;
    }

}
