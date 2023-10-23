package src.app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import src.interface_adaptors.ViewManagerModel;
import src.view.ViewManager;
import src.interface_adaptors.QuestionViewModel;
import src.view.QuestionView;

public class Main {

    public static void main(String[] args) {
        JFrame application = new JFrame("Trivia Cash $$$$");
        application.setBounds(250, 150, 1200, 600);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        QuestionViewModel questionViewModel = new QuestionViewModel();

        QuestionView questionView = new QuestionView(questionViewModel);
        views.add(questionView, questionView.viewName);

        viewManagerModel.setActiveView(questionView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setVisible(true);
    }
}

