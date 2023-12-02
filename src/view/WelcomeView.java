package view;

import view.SignupView;
import view.LoginView;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JPanel {

    public final String viewName = "welcome";
    private final JButton logIn;
    private final JButton signUp;

    private final ViewManagerModel viewManagerModel;

    public WelcomeView(ViewManagerModel viewManagerModel) {

        this.viewManagerModel = viewManagerModel;


        JPanel buttons = new JPanel();
        logIn = new JButton("Log In");
        buttons.add(logIn);
        signUp = new JButton("Sign Up");
        buttons.add(signUp);

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(signUp)) {
                    viewManagerModel.setActiveView("sign up");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(logIn)) {
                    viewManagerModel.setActiveView("log in");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        add(buttons);

    }
}
