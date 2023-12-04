package app;

import data_access.FileUserDataAccessObject;

import data_access.QuestionStorageDataAccessObject;
import data_access.SelectModeDataAccessObject;
import entity.CommonQuestionStorage;
import entity.CommonUserFactory;
import entity.QuestionStorage;
import entity.User;
import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeleteViewModel;
import interface_adaptors.game_over.GameOverViewModel;
import interface_adaptors.login.LoginViewModel;
import interface_adaptors.logout.LogoutController;
import interface_adaptors.logout.LogoutPresenter;
import interface_adaptors.logout.LogoutViewModel;
import interface_adaptors.question.QuestionViewModel;
import interface_adaptors.select_mode.SelectModeViewModel;
import interface_adaptors.signup.SignupViewModel;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.select_colour.SelectColourViewModel;
import use_case.delete.DeleteInteractor;
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
        JFrame application = new JFrame("Totally Trivia!");
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

        // Initializes all the remaining Data Access Objects.

        SelectModeDataAccessObject selectModeDataAccessObject = new SelectModeDataAccessObject();

        // Initializes an empty User. This User will be filled in by sign up/log in.
        CommonUserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("", "",LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");

        QuestionStorage questionStorage = new CommonQuestionStorage();

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, user);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, userDataAccessObject, user);
        views.add(loginView, loginView.viewName);

        SelectModeView selectModeView = SelectModeUseCaseFactory.create(viewManagerModel, selectModeViewModel,
                selectModeDataAccessObject, questionStorage, questionViewModel);
        views.add(selectModeView, selectModeView.viewName);

        QuestionView questionView = QuestionUseCaseFactory.create(viewManagerModel, questionViewModel, gameOverViewModel, questionStorage);
        views.add(questionView, questionView.viewName);

        GameOverView gameOverView = GameOverUseCaseFactory.create(viewManagerModel, gameOverViewModel, userDataAccessObject, user);
        views.add(gameOverView, gameOverView.viewName);

        LogoutView logoutView = new LogoutView(new LogoutController(new LogoutInteractor(new LogoutPresenter(viewManagerModel, logoutViewModel), user)), logoutViewModel);
        views.add(logoutView, logoutView.viewName);

        AccountView accountView = new AccountView(viewManagerModel);
        views.add(accountView, accountView.viewName);

        MainScreenView mainScreenView = new MainScreenView(viewManagerModel);
        views.add(mainScreenView, mainScreenView.viewName);

        SelectColourView selectColourView = SelectColourUseCaseFactory.create(viewManagerModel, selectColourViewModel, user, userDataAccessObject, accountView, mainScreenView, questionView, selectModeView, gameOverView);
        views.add(selectColourView, selectColourView.viewName);

        WelcomeView welcomeView = new WelcomeView(viewManagerModel);
        views.add(welcomeView, welcomeView.viewName);

        String s = user.getName();
        DeleteController deleteController = DeleteUseCaseFactory.createUserDeleteUseCase(deleteViewModel, user, userDataAccessObject);

        DeleteView deleteView = new DeleteView(deleteController, deleteViewModel);
        views.add(deleteView, deleteView.viewName);

        viewManagerModel.setActiveView(welcomeView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

