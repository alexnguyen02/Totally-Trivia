package view;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeleteViewModel;
import interface_adaptors.login.LoginController;
import interface_adaptors.login.LoginViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewTest {
    @Test
    public void testViewName() {
        LoginController deleteController = new LoginController(null);
        LoginViewModel deleteViewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginView deleteView = new LoginView(deleteViewModel, deleteController, viewManagerModel);

        assertEquals("login", deleteView.viewName);
    }

    @Test
    public void testButtonsExist() {
        LoginController deleteController = new LoginController(null);
        LoginViewModel deleteViewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginView deleteView = new LoginView(deleteViewModel, deleteController, viewManagerModel);

        assertNotNull(deleteView.logIn);
        assertNotNull(deleteView.cancel);
    }

}