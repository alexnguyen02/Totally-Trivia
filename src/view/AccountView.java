package view;

import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountView extends JPanel {

    public String viewName = "account";
    public final JButton changeColour;
    public final JButton logOut;

    public final JButton deleteAccount;

    public final JButton back;

    public final ViewManagerModel viewManagerModel;


    public AccountView(ViewManagerModel viewManagerModel) {

        this.viewManagerModel = viewManagerModel;

        Box buttons = Box.createVerticalBox();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        changeColour = new JButton("Change Background Colour");
        changeColour.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(changeColour);
        logOut = new JButton("Log Out");
        logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(logOut);
        deleteAccount = new JButton("Delete Account");
        deleteAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(deleteAccount);
        back = new JButton("Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(buttons);
    }

    public void changeColour(Color colour) {
        this.setBackground(colour);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
}
