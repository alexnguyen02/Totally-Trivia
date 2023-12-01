package view;

import interface_adaptors.ViewManagerModel;
import view.SelectColourView;
import view.LogoutView;
import view.DeleteView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountView extends JPanel {

    public String viewName = "account";
    private final JButton changeColour;
    private final JButton logOut;

    private final JButton deleteAccount;

    private final ViewManagerModel viewManagerModel;

    private final SelectColourView selectColourView;

    private final LogoutView logoutView;

    private final DeleteView deleteView;

    public AccountView(ViewManagerModel viewManagerModel, SelectColourView selectColourView, LogoutView logoutView, DeleteView deleteView) {

        this.viewManagerModel = viewManagerModel;
        this.selectColourView = selectColourView;
        this.logoutView = logoutView;
        this.deleteView = deleteView;


        JPanel buttons = new JPanel();
        changeColour = new JButton("Change Background Colour");
        buttons.add(changeColour);
        logOut = new JButton("Log Out");
        buttons.add(logOut);
        deleteAccount = new JButton("Delete Account");
        buttons.add(deleteAccount);

        changeColour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(changeColour)) {
                    viewManagerModel.setActiveView(selectColourView.viewName);
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(logOut)) {
                    viewManagerModel.setActiveView(logoutView.viewName);
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(deleteAccount)) {
                    viewManagerModel.setActiveView(deleteView.viewName);
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        add(buttons);

    }
}
