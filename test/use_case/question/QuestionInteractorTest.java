package use_case.question;


import entity.AnswerPackage;
import entity.CommonQuestionStorage;
import entity.Question;
import entity.QuestionStorage;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionInteractorTest {

    public QuestionStorage createStorage() {
        ArrayList<Question> listOfQuestions = new ArrayList<>();

        ArrayList<String> answersOne = new ArrayList<>(Arrays.asList("Immortan Joe", "The People Eater", "Dementus", "Nux"));
        Question questionOne = new Question("Who is the main villain in Mad Max: Fury Road?", "Film", "easy", new AnswerPackage(answersOne, "Immortan Joe"));
        listOfQuestions.add(questionOne);

        ArrayList<String> answersTwo = new ArrayList<>(Arrays.asList("The Splendid Angharad", "Cheedo the Fragile", "Toast the Knowing", "The Illuminescent Ira"));
        Question questionTwo = new Question("Which of these characters is not one of the wives in Mad Max: Fury Road?", "Film", "hard", new AnswerPackage(answersTwo, "The Illuminescent Ira"));
        listOfQuestions.add(questionTwo);

        ArrayList<String> answersThree = new ArrayList<>(Arrays.asList("O+", "O-", "AB", "B-"));
        Question questionThree = new Question("What is Max Roctansky's blood type?", "Film", "easy", new AnswerPackage(answersThree, "O+"));
        listOfQuestions.add(questionThree);

        QuestionStorage questionStorage = new CommonQuestionStorage();
        questionStorage.setQuestions(listOfQuestions);
        return questionStorage;
    }

    @Test
    public void correctQuestionFirstQuestionTest() {
        QuestionInputData inputData = new QuestionInputData("Immortan Joe", 0);
        QuestionStorage questionStorage = createStorage();

        QuestionOutputBoundary successPresenter = new QuestionOutputBoundary() {
            @Override
            public void prepareSuccessView(QuestionOutputData outputData) {
                Assertions.assertTrue(outputData.correctness);
                Assertions.assertEquals(outputData.nextQuestion, questionStorage.getQuestions().get(1));
                Assertions.assertEquals(outputData.totalQuestions, 3);
                Assertions.assertEquals(outputData.difficulty, "easy");
            }

            public void prepareFailView(String message) {}

        };

        QuestionInputBoundary interactor = new QuestionInteractor(successPresenter, questionStorage);
        interactor.execute(inputData);
    }
}