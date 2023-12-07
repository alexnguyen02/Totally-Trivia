package view;

import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeleteState;
import interface_adaptors.delete.DeleteViewModel;
import interface_adaptors.signup.SignupState;
import interface_adaptors.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeleteView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "delete";

    private final DeleteViewModel deleteViewModel;


    private final DeleteController deleteController;

    public final JButton delete;

    public final JButton back;

    public final ViewManagerModel viewManagerModel;



    public DeleteView(DeleteController deletecontroller, DeleteViewModel deleteViewModel, ViewManagerModel viewManagerModel) {


        this.deleteController = deletecontroller;
        this.deleteViewModel = deleteViewModel;
        deleteViewModel.addPropertyChangeListener(this);

        this.viewManagerModel = viewManagerModel;

        JLabel title = new JLabel(DeleteViewModel.DELETE_BUTTON_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box buttons = Box.createHorizontalBox();
        delete = new JButton(DeleteViewModel.DELETE_BUTTON_LABEL);
        buttons.add(delete);
        back = new JButton("Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(back);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);

        this.add(buttons);

        delete.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(delete)) {
                            int confirmDialogResult = JOptionPane.showConfirmDialog(
                                    DeleteView.this,
                                    "Are you sure you want to delete account?",
                                    "Delete",
                                    JOptionPane.YES_NO_OPTION
                            );

                            if (confirmDialogResult == JOptionPane.YES_OPTION) {
                                // User pressed "Yes" for delete
                                DeleteState currentState = deleteViewModel.getState();
                                String s = currentState.getUsernames();
                                deleteController.execute(s);
                            }


                        }
                    }
                }
        );

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(back)) {
                    viewManagerModel.setActiveView("account");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

    }

    public void changeColour(Color colour) {
        this.setBackground(colour);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object obj = evt.getNewValue();
        if(obj instanceof DeleteState){
            DeleteState state = (DeleteState) obj;
            JOptionPane.showMessageDialog(this, state.getUsernames());
        }else {
            SignupState state = (SignupState) obj;
            if (state.getUsernameError() != null) {
                JOptionPane.showMessageDialog(this, state.getUsernameError());
            }
        }
    }
}