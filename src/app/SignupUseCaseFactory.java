package src.app;

import src.entity.UserFactory;
import src.interface_adaptors.ViewManagerModel;
import src.interface_adaptors.login.LoginViewModel;
import src.interface_adaptors.signup.SignupController;
import src.interface_adaptors.signup.SignupPresenter;
import src.interface_adaptors.signup.SignupViewModel;
import src.use_case.signup.SignupInputBoundary;
import src.use_case.signup.SignupOutputBoundary;
import src.use_case.signup.SignupUserDataAccessInterface;
import src.view.SignUpView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignUpView.SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new SignUpView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
