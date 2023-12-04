package view;

import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountViewTest {

    @Test
    public void accountViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        AccountView accountView = new AccountView(viewManagerModel);

        assertEquals(accountView.changeColour.getText(), "Change Background Colour");
        assertEquals(accountView.logOut.getText(), "Log Out");
        assertEquals(accountView.deleteAccount.getText(), "Delete Account");
        assertEquals(accountView.back.getText(), "Bacl");
    }
    @Test
    public void changeColourTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        AccountView accountView = new AccountView(viewManagerModel);
        accountView.changeColour(Color.BLACK);
        assertEquals(accountView.getBackground(), Color.BLACK);
    }
}