package test;

import entity.AnswerPackage;
import entity.Question;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class QuestionEntityTest {
    private Question question;

    @Before
    public void init(){
        String content = "What is the name of the town in which Lily and James Potter are buried?";
        String category = "Books";
        String difficultyLevel = "Medium";

        ArrayList<String> possibleAnswers = new ArrayList<>(Arrays.asList((new String[]{"Tinworth", "Godric's Hollow", "Hogsmeade", "Mould-on-the-Wold"})));
        AnswerPackage answerPackage = new AnswerPackage(
                possibleAnswers,
                "Godric's Hollow"
        );

        question = new Question(content, category, difficultyLevel, answerPackage);
    }

    @org.junit.Test
    public void getContent(){
        assertEquals(
                "What is the name of the town in which Lily and James Potter are buried?",
                question.getContent());
    }

    @org.junit.Test
    public void getDifficultyLevel(){
        assertEquals(
                "Medium",
                question.getDifficultyLevel());
    }

    @org.junit.Test
    public void getCategory(){
        assertEquals(
                "Books",
                question.getCategory());
    }

    @org.junit.Test
    public void getAnswerPackage(){
        assertNotNull(question.getAnswerPackage());
    }
}
