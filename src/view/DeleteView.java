package view;

//import interface_adaptors.clear_users.ClearController;
//import interface_adapter.clear_users.ClearState;
//import interface_adapter.clear_users.ClearViewModel;
import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeleteState;
import interface_adaptors.delete.DeleteViewModel;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SignupState;
import interface_adaptors.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeleteView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "delete";

    private final DeleteViewModel deleteViewModel;


    private final DeleteController deleteController;

    private final JButton delete;


    public DeleteView(DeleteController deletecontroller, DeleteViewModel deleteViewModel) {


        this.deleteController = deletecontroller;
        this.deleteViewModel = deleteViewModel;
        deleteViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(DeleteViewModel.DELETE_BUTTON_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);



        JPanel buttons = new JPanel();
        delete = new JButton(DeleteViewModel.DELETE_BUTTON_LABEL);
        buttons.add(delete);

        delete.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(delete)) {
                            String s = deleteViewModel.getState().getUsernames();
                            deleteController.execute(s);

                        }
                    }
                }
        );



        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);

        this.add(buttons);
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