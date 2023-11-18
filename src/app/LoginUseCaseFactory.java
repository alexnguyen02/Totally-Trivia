package src.app;

import src.entity.CommonUserFactory;
import src.entity.UserFactory;
import src.entity.User;
import src.interface_adaptors.ViewManagerModel;
import src.interface_adaptors.logged_in.LoggedInViewModel;
import src.interface_adaptors.login.LoginController;
import src.interface_adaptors.login.LoginPresenter;
import src.interface_adaptors.login.LoginViewModel;
import src.use_case.login.LoginInputBoundary;
import src.use_case.login.LoginOutputBoundary;
import src.use_case.login.LoginInteractor;
import src.use_case.login.LoginUserDataAccessInterface;
import src.view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
