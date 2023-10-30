package src.use_case;

import java.util.ArrayList;

public interface QuestionAnswerFactory {
    ArrayList<String> getQuestionList (String category, String difficultyLevel, int numberOfQuestions);

    ArrayList<String> getAnswerList();
}
