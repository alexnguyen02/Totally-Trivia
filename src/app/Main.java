package app;

import app.LoginUseCaseFactory;
import data_access.FileUserDataAccessObject;

import data_access.QuestionStorageDataAccessObject;
import data_access.SelectModeDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import interface_adaptors.delete.DeleteViewModel;
import interface_adaptors.game_over.GameOverController;
import interface_adaptors.game_over.GameOverPresenter;
import interface_adaptors.game_over.GameOverViewModel;
import interface_adaptors.login.LoginViewModel;
import interface_adaptors.logged_in.LoggedInViewModel;
import interface_adaptors.logout.LogoutController;
import interface_adaptors.logout.LogoutPresenter;
import interface_adaptors.logout.LogoutViewModel;
import interface_adaptors.question.QuestionViewModel;
import interface_adaptors.select_mode.SelectModeViewModel;
import interface_adaptors.signup.SignupViewModel;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.select_colour.SelectColourViewModel;
//import data_access.SelectColourDataAccessObject;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.select_mode.SelectModeDataObjectInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Trivia Cash");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Initates the ViewManagerModel, which will manage all Views.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Initiates all the ViewModels for each use case.
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        QuestionViewModel questionViewModel = new QuestionViewModel();
        GameOverViewModel gameOverViewModel = new GameOverViewModel();
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        LogoutViewModel logoutViewModel = new LogoutViewModel();
        SelectColourViewModel selectColourViewModel = new SelectColourViewModel();
        SelectModeViewModel selectModeViewModel = new SelectModeViewModel();

        // Initiates the FileUserDataAccessObject.
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Initialize InMemoryDataAccessObject (for testing purpose); The actual Data Access Object is calling API
        SelectModeDataObjectInterface selectModeAccessObject;
        selectModeAccessObject = new SelectModeDataAccessObject();

        // Initializes all the remaining Data Access Objects.

        SelectModeDataAccessObject selectModeDataAccessObject = new SelectModeDataAccessObject();
        QuestionStorageDataAccessObject questionStorageDataAccessObject = new QuestionStorageDataAccessObject();
//        Color defaultColour = new Color(255);
//        SelectColourDataAccessObject selectColourDataAccessObject = new SelectColourDataAccessObject(defaultColour);

        // Initializes an empty User. This User will be filled in by sign up/log in.
        CommonUserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("", "",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, new Color(255));

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, user);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, user);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        SelectModeView selectModeView = SelectModeUseCaseFactory.create(viewManagerModel, selectModeViewModel,
                selectModeDataAccessObject, questionStorageDataAccessObject, questionViewModel);
        views.add(selectModeView, selectModeView.viewName);

        QuestionView questionView = QuestionUseCaseFactory.create(viewManagerModel, questionViewModel, gameOverViewModel, questionStorageDataAccessObject);
        views.add(questionView, questionView.viewName);

        GameOverView gameOverView = GameOverUseCaseFactory.create(viewManagerModel, gameOverViewModel, userDataAccessObject, user);
        views.add(gameOverView, gameOverView.viewName);

        LogoutView logoutView = new LogoutView(new LogoutController(null), logoutViewModel);
        views.add(logoutView, logoutView.viewName);

        SelectColourView selectColourView = SelectColourUseCaseFactory.create(viewManagerModel, selectColourViewModel, user, userDataAccessObject);
        views.add(selectColourView, selectColourView.viewName);

        AccountView accountView = new AccountView(viewManagerModel);
        views.add(accountView, accountView.viewName);

        MainScreenView mainScreenView = new MainScreenView(viewManagerModel);
        views.add(mainScreenView, mainScreenView.viewName);

        WelcomeView welcomeView = new WelcomeView(viewManagerModel);
        views.add(welcomeView, welcomeView.viewName);

        viewManagerModel.setActiveView(welcomeView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

