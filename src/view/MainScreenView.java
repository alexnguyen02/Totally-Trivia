package view;

import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenView extends JPanel {

    public String viewName = "main screen";
    public final JButton playGame;
    public final JButton account;
    public final JLabel title;

    private final ViewManagerModel viewManagerModel;


    public MainScreenView(ViewManagerModel viewManagerModel) {

        this.viewManagerModel = viewManagerModel;

        title = new JLabel("Totally Trivia!");
        title.setFont(new Font("Impact", Font.PLAIN, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        Box buttons = Box.createVerticalBox();
        buttons.add(Box.createVerticalStrut(30));
        playGame = new JButton("Play Game");
        playGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(playGame);
        account = new JButton("Account");
        account.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(account);

        playGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(playGame)) {
                    viewManagerModel.setActiveView("select mode");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(account)) {
                    viewManagerModel.setActiveView("account");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(title);
        add(buttons);

    }

    public void changeColour(Color colour) {
        this.setBackground(colour);
    }
}
