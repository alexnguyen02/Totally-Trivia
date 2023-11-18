package src.view;

import src.interface_adaptors.signup.SignupController;
import src.interface_adaptors.signup.SignupViewModel;

public class SignupView {
    public String viewName;

    private SignupController signupController;

    private SignupViewModel signupViewModel;

    public SignupView(SignupController signupController, SignupViewModel signupViewModel) {
        this.signupController = signupController;
        this.signupViewModel = signupViewModel;
    }
}
