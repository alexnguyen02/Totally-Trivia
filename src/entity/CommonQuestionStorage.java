package entity;

import java.util.ArrayList;

public class CommonQuestionStorage implements QuestionStorage {

    private ArrayList<Question> listOfQuestions;

    public CommonQuestionStorage() {
        this.listOfQuestions = new ArrayList<>();
    }

    public ArrayList<Question> getQuestions() {
        return this.listOfQuestions;
    }

    public void setQuestions(ArrayList<Question> newQuestions) {
        this.listOfQuestions = newQuestions;
    }
}
