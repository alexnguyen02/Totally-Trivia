package view;

import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MainScreenViewTest {
    @Test
    public void mainScreenViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MainScreenView mainScreenView = new MainScreenView(viewManagerModel);

        assertEquals(mainScreenView.title.getText(), "Totally Trivia!");
        assertEquals(mainScreenView.playGame.getText(), "Play Game");
        assertEquals(mainScreenView.account.getText(), "Account");
    }
    @Test
    public void changeColourTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MainScreenView mainScreenView = new MainScreenView(viewManagerModel);
        mainScreenView.changeColour(Color.BLACK);
        assertEquals(mainScreenView.getBackground(), Color.BLACK);
    }
}