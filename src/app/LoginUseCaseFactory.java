package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import entity.User;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.LoginController;
import interface_adaptors.login.LoginPresenter;
import interface_adaptors.login.LoginViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            User user) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, userDataAccessObject, user);
            return new LoginView(loginViewModel, loginController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            User user) throws IOException {

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, user);

        return new LoginController(loginInteractor);
    }
}
