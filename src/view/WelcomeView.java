package view;

import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JPanel {

    public final String viewName = "welcome";
    public final JButton logIn;
    public final JButton signUp;
    public final JLabel title;

    private final ViewManagerModel viewManagerModel;

    public WelcomeView(ViewManagerModel viewManagerModel) {

        this.viewManagerModel = viewManagerModel;

        title = new JLabel("Welcome to Totally Trivia!");
        title.setFont(new Font("Impact", Font.PLAIN, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        Box buttons = Box.createVerticalBox();

        buttons.add(Box.createVerticalStrut(20));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        logIn = new JButton("Log In");
        logIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(logIn);
        signUp = new JButton("Sign Up");
        signUp.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(title);
        add(buttons);

    }
}
