package view;

import interface_adaptors.logout.LogoutController;
import interface_adaptors.logout.LogoutViewModel;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SignupViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupViewTest {
    @Test
    public void testViewName() {
        SignupController deleteController = new SignupController(null);
        SignupViewModel deleteViewModel = new SignupViewModel();
        SignupView deleteView = new SignupView(deleteController, deleteViewModel);

        assertEquals("sign up", deleteView.viewName);
    }

    @Test
    public void testButtonsExist() {
        SignupController deleteController = new SignupController(null);
        SignupViewModel deleteViewModel = new SignupViewModel();
        SignupView deleteView = new SignupView(deleteController, deleteViewModel);

        assertNotNull(deleteView.signUp);
        assertNotNull(deleteView.clear);
    }

}