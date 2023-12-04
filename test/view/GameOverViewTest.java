package view;

import app.GameOverUseCaseFactory;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.game_over.GameOverPresenter;
import interface_adaptors.game_over.GameOverState;
import interface_adaptors.game_over.GameOverViewModel;
import use_case.game_over.GameOverOutputBoundary;
import use_case.game_over.GameOverUserDataAccessInterface;
import org.junit.Test;

import java.awt.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class GameOverViewTest {
    public GameOverView createGameOverView() {
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GameOverOutputBoundary gameOverOutputBoundary = new GameOverPresenter(viewManagerModel);
        GameOverUserDataAccessInterface userDataAccessObject = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Furiosa", "RememberMe", LocalDateTime.now(), 50, "Red");
        GameOverView gameOverView = GameOverUseCaseFactory.create(viewManagerModel, gameOverViewModel, userDataAccessObject, user);
        return gameOverView;
    }
    @Test
    public void buttonMainMenuTest() {
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GameOverUserDataAccessInterface userDataAccessObject = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Furiosa", "RememberMe", LocalDateTime.now(), 45, "Red");
        User for_repository = userFactory.create("","", LocalDateTime.now(), 0, "White");
        for_repository.copyUser(user);
        userDataAccessObject.save(for_repository);
        GameOverView gameOverView = GameOverUseCaseFactory.create(viewManagerModel, gameOverViewModel, userDataAccessObject, user);
        gameOverView.gameOverController.execute("main screen", 5);
        //tests if points was written to the DAO
        assertEquals(userDataAccessObject.get("Furiosa").getPoints(), 50);
        //tests that the view was switched
        assertEquals(viewManagerModel.getActiveView(), "main screen");
    }
    public void buttonPlayAgainTest() {
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GameOverUserDataAccessInterface userDataAccessObject = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Furiosa", "RememberMe", LocalDateTime.now(), 45, "Red");
        GameOverView gameOverView = GameOverUseCaseFactory.create(viewManagerModel, gameOverViewModel, userDataAccessObject, user);
        gameOverView.gameOverController.execute("select mode", 5);
        //tests if points was written to the DAO
        assertEquals(userDataAccessObject.get("Furiosa").getPoints(), 49);
        //tests that the view was switched
        assertEquals(viewManagerModel.getActiveView(), "select mode");
    }
    @Test
    public void updateViewTest() {
        GameOverView gameOverView = createGameOverView();
        GameOverState gameOverState = new GameOverState(5, 4, 1, 4);
        GameOverState oldGameOverState = gameOverView.gameOverViewModel.getState();
        gameOverView.gameOverViewModel.setState(gameOverState);
        gameOverView.updateView();
        assertNotEquals(gameOverView.gameOverViewModel.getState(), oldGameOverState);
        assertEquals(gameOverView.gameOverViewModel.getState(), gameOverState);
        assertEquals(gameOverView.correct_answers.getText(), "Correct Answers: 4/5");
        assertEquals(gameOverView.points_earned.getText(), "Points Earned: 4");
    }

    @Test
    public void changeColourTest() {
        GameOverView gameOverView = createGameOverView();
        gameOverView.changeColour(Color.BLACK);
        assertEquals(gameOverView.getBackground(), Color.BLACK);
    }





}