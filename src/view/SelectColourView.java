package view;

import interface_adaptors.select_colour.SelectColourController;
import interface_adaptors.select_colour.SelectColourViewModel;
import interface_adaptors.select_colour.SelectColourState;

import java.awt.Color;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SelectColourView extends JPanel implements ActionListener, PropertyChangeListener {
    public String viewName = "select colour";

    private final SelectColourViewModel selectColourViewModel;

    private final SelectColourController selectColourController;

    private final String[] colourList = new String[]{"Black", "Blue", "Cyan", "Dark Gray",
            "Gray", "Green", "Light_Gray", "Magenta", "Orange", "Pink", "Red",
            "White", "Yellow"};

    private final JComboBox<String> colourDropdown = new JComboBox<>(colourList);

    public SelectColourView(SelectColourViewModel selectColourViewModel, SelectColourController selectColourController){
        this.selectColourViewModel = selectColourViewModel;
        this.selectColourController = selectColourController;
        this.selectColourViewModel.addPropertyChangeListener(this);

        colourDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectColourState currentState = selectColourViewModel.getState();

                String selectedColourString = colourDropdown.getSelectedItem().toString();

                Color selectedColour = convertColorNameToColor(selectedColourString);

                SelectColourController.execute(selectedColour);

                // Update the state in the view model
                selectColourViewModel.setState(currentState);
            }
        });
        add(colourDropdown);
    }

    private Color convertColorNameToColor(String colorName) {
        switch (colorName) {
            case "Black":
                return Color.BLACK;
            case "Blue":
                return Color.BLUE;
            case "Cyan":
                return Color.CYAN;
            case "Dark Gray":
                return Color.DARK_GRAY;
            case "Gray":
                return Color.GRAY;
            case "Green":
                return Color.GREEN;
            case "Light_Gray":
                return Color.LIGHT_GRAY;
            case "Magenta":
                return Color.MAGENTA;
            case "Orange":
                return Color.ORANGE;
            case "Pink":
                return Color.PINK;
            case "Red":
                return Color.RED;
            case "White":
                return Color.WHITE;
            case "Yellow":
                return Color.YELLOW;
            default:
                return Color.BLACK;
        }
    }


    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SelectColourState) {
            SelectColourState selectColourState = (SelectColourState) evt.getNewValue();
            if (selectColourState.getColour() != null) {
                String colour = convertColorToColourName(selectColourState.getColour());

                JOptionPane.showMessageDialog(this, "The background colour was changed to " + colour + "!" );
            }
        }


    }

    private String convertColorToColourName(Color color) {
        if (color.equals(Color.BLACK)) {
            return "Black";
        } else if (color.equals(Color.BLUE)) {
            return "Blue";
        } else if (color.equals(Color.CYAN)) {
            return "Cyan";
        } else if (color.equals(Color.DARK_GRAY)) {
            return "Dark Gray";
        } else if (color.equals(Color.GRAY)) {
            return "Gray";
        } else if (color.equals(Color.GREEN)) {
            return "Green";
        } else if (color.equals(Color.LIGHT_GRAY)) {
            return "Light Gray";
        } else if (color.equals(Color.MAGENTA)) {
            return "Magenta";
        } else if (color.equals(Color.ORANGE)) {
            return "Orange";
        } else if (color.equals(Color.PINK)) {
            return "Pink";
        } else if (color.equals(Color.RED)) {
            return "Red";
        } else if (color.equals(Color.WHITE)) {
            return "White";
        } else if (color.equals(Color.YELLOW)) {
            return "Yellow";
        } else {
            return "Black";
        }
    }



}
