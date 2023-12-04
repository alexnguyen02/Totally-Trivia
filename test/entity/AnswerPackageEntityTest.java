package test;
package entity;

import entity.AnswerPackage;
import entity.Question;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AnswerPackageEntityTest {
    private AnswerPackage answerPackage;

    @Before
    public void init(){
        ArrayList<String> possibleAnswers = new ArrayList<>(Arrays.asList((new String[]{"Tinworth", "Godric's Hollow", "Hogsmeade", "Mould-on-the-Wold"})));
        String correctAnswer = "Godric's Hollow";
        answerPackage = new AnswerPackage(
                possibleAnswers,
                correctAnswer
        );
    }

    @org.junit.Test
    public void getPossibleAnswers(){
        ArrayList<String> listOfAnswers = answerPackage.getPossibleAnswers();
        assertEquals("Tinworth", listOfAnswers.get(0));
        assertEquals("Godric's Hollow", listOfAnswers.get(1));
        assertEquals("Hogsmeade", listOfAnswers.get(2));
        assertEquals("Mould-on-the-Wold", listOfAnswers.get(3));
    }

    @org.junit.Test
    public void getCorrectAnswer(){
        String correctAnswer = answerPackage.getCorrectAnswer();
        assertEquals("Godric's Hollow", correctAnswer);
    }
}
