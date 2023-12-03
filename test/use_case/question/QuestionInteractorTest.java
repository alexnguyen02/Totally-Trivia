package use_case.question;


import entity.AnswerPackage;
import entity.CommonQuestionStorage;
import entity.Question;
import entity.QuestionStorage;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.game_over.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class QuestionInteractorTest {
    QuestionStorage questionStorage;

    @BeforeEach
    public void init() {
        ArrayList<Question> listOfQuestions = new ArrayList<>();

        ArrayList<String> answersOne = new ArrayList<>();
        answersOne.add("Immortan Joe");
        answersOne.add("The People Eater");
        answersOne.add("Dementus");
        answersOne.add("Nux");
        Question questionOne = new Question("Who is the main villain in Mad Max: Fury Road?", "Film", "easy", new AnswerPackage(answersOne, "Immortan Joe"));
        listOfQuestions.add(questionOne);

        ArrayList<String> answersTwo = new ArrayList<>();
        answersOne.add("The Splendid Angharad");
        answersOne.add("Cheedo the Fragile");
        answersOne.add("Toast the Knowing");
        answersOne.add("The Illuminescent Ira");
        Question questionTwo = new Question("Which of these characters is not one of the wives in Mad Max: Fury Road?", "Film", "hard", new AnswerPackage(answersTwo, "The Illuminescent Ira"));
        listOfQuestions.add(questionTwo);

        ArrayList<String> answersThree = new ArrayList<>();
        answersOne.add("O+");
        answersOne.add("O-");
        answersOne.add("AB");
        answersOne.add("B-");
        Question questionThree = new Question("What is Max Roctansky's blood type?", "Film", "easy", new AnswerPackage(answersThree, "O+"));
        listOfQuestions.add(questionThree);

        questionStorage.setQuestions(listOfQuestions);
    }

    @Test
    public void correctQuestionFirstQuestionTest() {
        QuestionInputData inputData = new QuestionInputData("Immortan Joe", 0);
        QuestionOutputBoundary successPresenter = new QuestionOutputBoundary() {
            @Override
            public void prepareSuccessView(QuestionOutputData outputData) {
                assertEquals(outputData.correctness, true);
                assertEquals(outputData.nextQuestion, questionStorage.getQuestions().get(1));
                assertEquals(outputData.totalQuestions, 3);
                assertEquals(outputData.difficulty, "easy");
            }

            public void prepareFailView(String message) {}

        };

        QuestionInputBoundary interactor = new QuestionInteractor(successPresenter, questionStorage);
        interactor.execute(inputData);
    }
}