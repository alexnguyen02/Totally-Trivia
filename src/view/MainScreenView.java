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

    private final SelectModeView selectModeView;

    private final AccountView accountView;

    public MainScreenView(ViewManagerModel viewManagerModel, SelectModeView selectModeView, AccountView accountView) {

        this.viewManagerModel = viewManagerModel;
        this.selectModeView = selectModeView;
        this.accountView = accountView;


        JPanel buttons = new JPanel();
        playGame = new JButton("Play Game");
        buttons.add(playGame);
        account = new JButton("Account");
        buttons.add(account);

        playGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(playGame)) {
                    viewManagerModel.setActiveView(selectModeView.viewName);
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(account)) {
                    viewManagerModel.setActiveView(accountView.viewName);
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        add(buttons);

    }
}
