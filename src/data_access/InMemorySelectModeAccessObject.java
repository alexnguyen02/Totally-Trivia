package src.data_access;

import src.entity.AnswerPackage;
import src.entity.Question;
import src.use_case.select_mode.SelectModeDataObjectInterface;

import java.util.*;

public class InMemorySelectModeAccessObject implements SelectModeDataObjectInterface {

    private final Map<Integer, Question> easyQuestions = new HashMap<>();

    private final Map<Integer, Question> mediumQuestions = new HashMap<>();

    private final Map<Integer, Question> hardQuestions = new HashMap<>();

    public InMemorySelectModeAccessObject(){
        ArrayList<String> possibleAnswersOne = new ArrayList<>(List.of(new String[]{"Dog", "Cat", "Both", "Neither"}));
        AnswerPackage answerPackageOne = new AnswerPackage(possibleAnswersOne, "Dog");
        Question questionOne = new Question(
                "Dog or Cat?",
                "Animals",
                "easy",
                answerPackageOne);
        easyQuestions.put(1, questionOne);


        ArrayList<String> possibleAnswersTwo = new ArrayList<>(List.of(new String[]{"Dog", "Cat", "Both", "Neither"}));
        AnswerPackage answerPackageTwo = new AnswerPackage(possibleAnswersTwo, "Dog");
        Question questionTwo = new Question(
                "Dog or Cat?",
                "Animals",
                "easy",
                answerPackageOne);
        easyQuestions.put(2, questionOne);
    }


    @Override
    public ArrayList<Question> getQuestions(String category, String difficultyLevel, int numOfQuestions) {
        ArrayList<Question> listOfQuestions= new ArrayList<>();

        /// For now, we will assume that all questions have the same category, which is "Animals" in this case.
        if (difficultyLevel.equals("easy")){
            for(int i = 0; i < numOfQuestions; i++){
                listOfQuestions.add(easyQuestions.get(i));
            }
        } else if (difficultyLevel.equals("medium")) {
            for(int i = 0; i < numOfQuestions; i++){
                listOfQuestions.add(mediumQuestions.get(i));
            }
        } else {
            for(int i = 0; i < numOfQuestions; i++){
                listOfQuestions.add(hardQuestions.get(i));
            }
        }
        return listOfQuestions;
    }
}
