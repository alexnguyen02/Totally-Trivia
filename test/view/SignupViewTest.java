package view;

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
        SignupController signUpController = new SignupController(null);
        SignupViewModel signUpViewModel = new SignupViewModel();
        SignupView signUpView = new SignupView(signUpController, signUpViewModel);

        assertNotNull(signUpView.signUp);
        assertNotNull(signUpView.cancel);
    }

}