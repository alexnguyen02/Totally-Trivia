package use_case;

import entity.Question;

import java.util.ArrayList;

public interface QuestionStorageDataAccessInterface {

    ArrayList<Question> getQuestions();

    void setQuestions(ArrayList<Question> newQuestions);
}
