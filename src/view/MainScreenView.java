package view;

import interface_adaptors.ViewManagerModel;
import view.SelectModeView;
import view.AccountView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenView extends JPanel {

    public String viewName = "main screen";
    private final JButton playGame;
    private final JButton account;

    private final ViewManagerModel viewManagerModel;


    public MainScreenView(ViewManagerModel viewManagerModel) {

        this.viewManagerModel = viewManagerModel;


        JPanel buttons = new JPanel();
        playGame = new JButton("Play Game");
        buttons.add(playGame);
        account = new JButton("Account");
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

        add(buttons);

    }
}
