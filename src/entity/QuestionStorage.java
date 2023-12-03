package entity;

import java.util.ArrayList;

public interface QuestionStorage {

    ArrayList<Question> getQuestions();

    void setQuestions(ArrayList<Question> newQuestions);
}
