package view;

import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountView extends JPanel {

    public String viewName = "account";
    private final JButton changeColour;
    private final JButton logOut;

    private final JButton deleteAccount;

    private final JButton back;

    private final ViewManagerModel viewManagerModel;


    public AccountView(ViewManagerModel viewManagerModel) {

        this.viewManagerModel = viewManagerModel;


        JPanel buttons = new JPanel();
        changeColour = new JButton("Change Background Colour");
        buttons.add(changeColour);
        logOut = new JButton("Log Out");
        buttons.add(logOut);
        deleteAccount = new JButton("Delete Account");
        buttons.add(deleteAccount);

        back = new JButton("Back");
        buttons.add(back);


        changeColour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(changeColour)) {
                    viewManagerModel.setActiveView("select colour");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(logOut)) {
                    viewManagerModel.setActiveView("logout");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(deleteAccount)) {
                    viewManagerModel.setActiveView("delete");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(back)) {
                    viewManagerModel.setActiveView("main screen");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        add(buttons);

    }
}
