package data_access;

import entity.AnswerPackage;
import entity.Question;
import use_case.select_mode.SelectModeDataObjectInterface;

import java.util.*;

public class InMemorySelectModeAccessObject implements SelectModeDataObjectInterface {

    private final Map<Integer, Question> easyQuestions = new HashMap<Integer,Question>();

    private final Map<Integer, Question> mediumQuestions = new HashMap<Integer,Question>();

    private final Map<Integer, Question> hardQuestions = new HashMap<Integer,Question>();

    public InMemorySelectModeAccessObject(){
        ArrayList<String> possibleAnswersOne = new ArrayList<>(Arrays.asList((new String[]{"Dog", "Cat", "Both", "Neither"})));
        AnswerPackage answerPackageOne = new AnswerPackage(possibleAnswersOne, "Dog");
        Question questionOne = new Question(
                "Dog or Cat?",
                "Animals",
                "Easy",
                answerPackageOne);
        easyQuestions.put(1, questionOne);


        ArrayList<String> possibleAnswersTwo = new ArrayList<>(Arrays.asList((new String[]{"Dog", "Cat", "Both", "Neither"})));
        AnswerPackage answerPackageTwo = new AnswerPackage(possibleAnswersTwo, "Dog");
        Question questionTwo = new Question(
                "Dog or Cat?",
                "Animals",
                "Easy",
                answerPackageTwo);
        easyQuestions.put(2, questionTwo);
    }


    @Override
    public ArrayList<Question> getQuestions(String category, String difficultyLevel, String numOfQuestions) {
        ArrayList<Question> listOfQuestions= new ArrayList<>();
        int numberOfQuestions = Integer.parseInt(numOfQuestions);

        /// For now, we will assume that all questions have the same category, which is "Animals" in this case.
        if (difficultyLevel.equals("Easy")){
            for(Map.Entry<Integer, Question> entry: easyQuestions.entrySet()){
                Question q = entry.getValue();
                listOfQuestions.add(q);
            }
        } else if (difficultyLevel.equals("Medium")) {
            for(int i = 0; i < numberOfQuestions; i++){
                Question q = mediumQuestions.get(i);
                listOfQuestions.add(q);
            }
        } else {
            for(int i = 0; i < numberOfQuestions; i++){
                Question q = hardQuestions.get(i);
                listOfQuestions.add(q);
            }
        }

        return listOfQuestions;
    }
}
