package view;

import app.QuestionUseCaseFactory;
import entity.AnswerPackage;
import entity.CommonQuestionStorage;
import entity.Question;
import entity.QuestionStorage;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.game_over.GameOverViewModel;
import interface_adaptors.question.QuestionState;
import interface_adaptors.question.QuestionViewModel;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionViewTest {

    public ArrayList<Question> generateQuestions() {
        ArrayList<Question> listOfQuestions = new ArrayList<>();

        ArrayList<String> answersOne = new ArrayList<>();
        answersOne.add("Immortan Joe");
        answersOne.add("The People Eater");
        answersOne.add("Dementus");
        answersOne.add("Nux");
        Question questionOne = new Question("Who is the main villain in Mad Max: Fury Road?", "Film", "Easy", new AnswerPackage(answersOne, "Immortan Joe"));
        listOfQuestions.add(questionOne);

        ArrayList<String> answersTwo = new ArrayList<>();
        answersTwo.add("The Splendid Angharad");
        answersTwo.add("Cheedo the Fragile");
        answersTwo.add("Toast the Knowing");
        answersTwo.add("The Illuminescent Ira");
        Question questionTwo = new Question("Which of these characters is not one of the wives in Mad Max: Fury Road?", "Film", "Hard", new AnswerPackage(answersTwo, "The Illuminescent Ira"));
        listOfQuestions.add(questionTwo);

        return listOfQuestions;
    }
    @Test
    public void initiateQuestionViewTest() {
        QuestionUseCaseFactory factory = new QuestionUseCaseFactory();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        QuestionViewModel questionViewModel = new QuestionViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        QuestionStorage questionStorage = new CommonQuestionStorage();
        questionStorage.setQuestions(generateQuestions());
        QuestionView questionView = factory.create(viewManagerModel, questionViewModel, gameOverViewModel, questionStorage);

        assertEquals(questionView.question.getText(), "");
        assertEquals(questionView.answer1.getText(), "");
        assertEquals(questionView.answer2.getText(), "");
        assertEquals(questionView.answer3.getText(), "");
        assertEquals(questionView.answer4.getText(), "");
    }

    @Test
    public void updateQuestionViewTest() {
        QuestionUseCaseFactory factory = new QuestionUseCaseFactory();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        QuestionViewModel questionViewModel = new QuestionViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        QuestionStorage questionStorage = new CommonQuestionStorage();
        questionStorage.setQuestions(generateQuestions());
        QuestionView questionView = factory.create(viewManagerModel, questionViewModel, gameOverViewModel, questionStorage);

        questionViewModel.updateViewModel(questionStorage.getQuestions().get(0));
        questionView.updateView();
        assertEquals(questionView.question.getText(), "Who is the main villain in Mad Max: Fury Road?");
        assertEquals(questionView.answer1.getText(), "Immortan Joe");
        assertEquals(questionView.answer2.getText(), "The People Eater");
        assertEquals(questionView.answer3.getText(), "Dementus");
        assertEquals(questionView.answer4.getText(), "Nux");
    }
    @Test
    public void correctAnswerQuestionViewTest() {
        QuestionUseCaseFactory factory = new QuestionUseCaseFactory();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        QuestionViewModel questionViewModel = new QuestionViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        QuestionStorage questionStorage = new CommonQuestionStorage();
        questionStorage.setQuestions(generateQuestions());
        QuestionView questionView = factory.create(viewManagerModel, questionViewModel, gameOverViewModel, questionStorage);

        questionView.questionController.execute("Immortan Joe", 0);
        assertEquals(questionView.question.getText(), "Which of these characters is not one of the wives in Mad Max: Fury Road?");
        assertEquals(questionView.answer1.getText(), "The Splendid Angharad");
        assertEquals(questionView.answer2.getText(), "Cheedo the Fragile");
        assertEquals(questionView.answer3.getText(), "Toast the Knowing");
        assertEquals(questionView.answer4.getText(), "The Illuminescent Ira");
        QuestionState questionState = questionViewModel.getState();
        assertEquals(questionState.getQuestionsCorrect(), 0);
        assertEquals(questionState.getTotalQuestions(), 2);
        assertEquals(questionState.getNewQuestion(), questionStorage.getQuestions().get(1));
        assertEquals(questionState.getQuestionNum(), 0);
        assertEquals(questionState.getTotalPoints(), 1);
    }

    @Test
    public void changeColourTest() {
        QuestionUseCaseFactory factory = new QuestionUseCaseFactory();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        QuestionViewModel questionViewModel = new QuestionViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        QuestionStorage questionStorage = new CommonQuestionStorage();
        questionStorage.setQuestions(generateQuestions());
        QuestionView questionView = factory.create(viewManagerModel, questionViewModel, gameOverViewModel, questionStorage);
        questionView.changeColour(Color.BLACK);
        assertEquals(questionView.getBackground(), Color.BLACK);
    }

    @Test
    public void rightOrWrongTest() {
        QuestionUseCaseFactory factory = new QuestionUseCaseFactory();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        QuestionViewModel questionViewModel = new QuestionViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        QuestionStorage questionStorage = new CommonQuestionStorage();
        questionStorage.setQuestions(generateQuestions());
        QuestionView questionView = factory.create(viewManagerModel, questionViewModel, gameOverViewModel, questionStorage);
        questionView.questionController.execute("Immortan Joe", 0);
        questionView.rightOrWrong();
        QuestionState questionState = questionViewModel.getState();
        assertEquals(questionState.getQuestionNum(), 1);
        assertEquals(questionState.getQuestionCorrect(), null);
    }

    @Test
    public void gameOverTest() {
        QuestionUseCaseFactory factory = new QuestionUseCaseFactory();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        QuestionViewModel questionViewModel = new QuestionViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        QuestionStorage questionStorage = new CommonQuestionStorage();
        questionStorage.setQuestions(generateQuestions());
        QuestionView questionView = factory.create(viewManagerModel, questionViewModel, gameOverViewModel, questionStorage);

        QuestionState gameOverState = new QuestionState();
        gameOverState.setQuestionsCorrect(2);
        gameOverState.setQuestionNum(1);
        gameOverState.setTotalPoints(4);
        gameOverState.setNewQuestion(null);
        gameOverState.setQuestionCorrect(true);
        questionViewModel.setState(gameOverState);
        questionView.gameOver();
        assertEquals(questionView.question.getText(), "");
        assertEquals(questionView.answer1.getText(), "");
        assertEquals(questionView.answer2.getText(), "");
        assertEquals(questionView.answer3.getText(), "");
        assertEquals(questionView.answer4.getText(), "");
        QuestionState new_state = new QuestionState();
        assertEquals(new_state.getTotalPoints(), questionViewModel.getState().getTotalPoints());
    }
}
