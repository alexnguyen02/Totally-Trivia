package data_access;

import entity.Question;

import java.util.ArrayList;

public class QuestionStorageDataAccessObject {

    private ArrayList<Question> listOfQuestions;

    public QuestionStorageDataAccessObject() {
        this.listOfQuestions = new ArrayList<>();
    }

    public ArrayList<Question> getQuestions() {
        return this.listOfQuestions;
    }

    public void setQuestions(ArrayList<Question> newQuestions) {
        this.listOfQuestions = newQuestions;
    }

}
