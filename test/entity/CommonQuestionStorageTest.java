package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class CommonQuestionStorageTest {

    private QuestionStorage questionStorage;

    @BeforeEach
    public void init() {
        questionStorage = new CommonQuestionStorage();
    }

    @Test
    public void getAndSetQuestionsTest() {
        ArrayList<Question> listOfQuestions = new ArrayList<>();

        ArrayList<String> answersOne = new ArrayList<>();
        answersOne.add("Immortan Joe");
        answersOne.add("The People Eater");
        answersOne.add("Dementus");
        answersOne.add("Nux");
        Question questionOne = new Question("Who is the main villain in Mad Max: Fury Road?", "Film", "easy", new AnswerPackage(answersOne, "Immortan Joe"));
        listOfQuestions.add(questionOne);

        ArrayList<String> answersTwo = new ArrayList<>();
        answersTwo.add("The Splendid Angharad");
        answersTwo.add("Cheedo the Fragile");
        answersTwo.add("Toast the Knowing");
        answersTwo.add("The Illuminescent Ira");
        Question questionTwo = new Question("Which of these characters is not one of the wives in Mad Max: Fury Road?", "Film", "hard", new AnswerPackage(answersTwo, "The Illuminescent Ira"));
        listOfQuestions.add(questionTwo);

        questionStorage.setQuestions(listOfQuestions);
        assertEquals(questionStorage.getQuestions(), listOfQuestions);
    }
}
