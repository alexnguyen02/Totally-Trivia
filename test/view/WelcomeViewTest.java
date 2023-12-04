package view;

import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WelcomeViewTest {

    @Test
    public void welcomeViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        WelcomeView welcomeView = new WelcomeView(viewManagerModel);

        assertEquals(welcomeView.title.getText(), "Welcome to Totally Trivia!");
        assertEquals(welcomeView.logIn.getText(), "Log In");
        assertEquals(welcomeView.signUp.getText(), "Sign Up");
    }

}