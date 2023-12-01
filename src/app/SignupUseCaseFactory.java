package app;

import entity.User;
import interface_adaptors.login.LoginViewModel;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SignupPresenter;
import interface_adaptors.signup.SignupViewModel;
import use_case.signup.SignupUserDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adaptors.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface userDataAccessObject, User user) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject, user);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject, User user) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory, user);

        return new SignupController(userSignupInteractor);
    }
}