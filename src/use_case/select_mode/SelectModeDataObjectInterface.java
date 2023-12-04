package use_case.select_mode;

import entity.Question;

import java.util.ArrayList;

public interface SelectModeDataObjectInterface {
    ArrayList<Question> getQuestions(String category, String difficultyLevel, String numOfQuestions);
}
