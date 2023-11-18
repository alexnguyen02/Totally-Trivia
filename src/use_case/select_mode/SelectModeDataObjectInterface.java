package src.use_case.select_mode;

import src.entity.Question;

import java.util.ArrayList;
import java.util.List;

public interface SelectModeDataObjectInterface {
    ArrayList<Question> getQuestions(String category, String difficultyLevel, int numOfQuestions);
}
