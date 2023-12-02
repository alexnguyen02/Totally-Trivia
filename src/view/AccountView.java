package view;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeletePresenter;
import interface_adaptors.delete.DeleteState;
import interface_adaptors.delete.DeleteViewModel;
import interface_adaptors.logged_in.LoggedInState;
import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
import use_case.delete.DeleteUserDataAccessInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

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

        // need a username in account
//        delete.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(delete)) {
//                            String s = username.getText();
//                            deleteController.execute(s);
//
//                        }
//                    }
//                }
//        );

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
    public static DeleteController createUserDeleteUseCase(DeleteViewModel deleteViewModel, DeleteUserDataAccessInterface deleteUserDataAccessInterface) {
        // Notice how we pass this method's parameters to the Presenter.
        DeleteOutputBoundary deleteOutputBoundary = new DeletePresenter(deleteViewModel);


        DeleteInputBoundary deleteInteractor = new DeleteInteractor(deleteUserDataAccessInterface, deleteOutputBoundary);

        return new DeleteController(deleteInteractor);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }




    //different prperty change

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        Object obj = evt.getNewValue();
//        if(obj instanceof DeleteState){
//            DeleteState state = (DeleteState) obj;
//            JOptionPane.showMessageDialog(this, state.getUsernames());
//        }else {
//            LoggedInState state = (LoggedInState) evt.getNewValue();
//            username.setText(state.getUsername());
//        }
//    }
}
