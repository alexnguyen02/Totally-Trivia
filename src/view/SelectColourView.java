package view;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.select_colour.SelectColourController;
import interface_adaptors.select_colour.SelectColourViewModel;
import interface_adaptors.select_colour.SelectColourState;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeEvent;

public class SelectColourView extends JPanel implements ActionListener, PropertyChangeListener {
    public String viewName = "select colour";

    private final SelectColourViewModel selectColourViewModel;

    private final SelectColourController selectColourController;

    private final AccountView accountView;

    private final MainScreenView mainScreenView;

    private final QuestionView questionView;

    private final SelectModeView selectModeView;
    private final GameOverView gameOverView;

    private final DeleteView deleteView;

    private final LogoutView logoutView;

    private final LabelDropDownPanel categoryInfo;

    private final String[] colourList = new String[]{"White", "Blue", "Cyan", "Dark Gray",
            "Gray", "Green", "Light_Gray", "Magenta", "Orange", "Pink", "Red",
            "Black", "Yellow"};

    private final JComboBox<String> colourDropdown = new JComboBox<>(colourList);

    private final JButton back;

    public SelectColourView(SelectColourViewModel selectColourViewModel, SelectColourController selectColourController, ViewManagerModel viewManagerModel,
                            AccountView accountView, MainScreenView mainScreenView, QuestionView questionView, SelectModeView selectModeView, GameOverView gameOverView,
                            DeleteView deleteView, LogoutView logoutView){
        this.selectColourViewModel = selectColourViewModel;
        this.selectColourController = selectColourController;
        this.selectColourViewModel.addPropertyChangeListener(this);
        this.accountView = accountView;
        this.mainScreenView = mainScreenView;
        this.questionView = questionView;
        this.selectModeView = selectModeView;
        this.gameOverView = gameOverView;
        this.deleteView = deleteView;
        this.logoutView = logoutView;


        categoryInfo = new LabelDropDownPanel(new JLabel("Select the Background Colour"), colourDropdown);

        Box buttons = Box.createVerticalBox();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        back = new JButton("Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(categoryInfo);
        buttons.add(Box.createVerticalStrut(50));
        buttons.add(back);
        add(buttons);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(back)) {
                    viewManagerModel.setActiveView("account");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        colourDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectColourState currentState = selectColourViewModel.getState();

                String selectedColour = colourDropdown.getSelectedItem().toString();

                selectColourController.execute(selectedColour);

                selectColourViewModel.setState(currentState);
            }
        });
    }

    private Color convertColourNameToColor(String colorName) {
        switch (colorName) {
            case "White":
                return Color.WHITE;
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
            case "Black":
                return Color.BLACK;
            case "Yellow":
                return Color.YELLOW;
            default:
                return Color.WHITE;
        }
    }

    public void actionPerformed(ActionEvent evt) {}


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SelectColourState) {
            SelectColourState selectColourState = (SelectColourState) evt.getNewValue();
            if (selectColourState.getColour() != null) {

                Color colour = convertColourNameToColor(selectColourState.getColour());
                this.setBackground(colour);

                accountView.changeColour(colour);
                mainScreenView.changeColour(colour);
                questionView.changeColour(colour);
                selectModeView.changeColour(colour);
                gameOverView.changeColour(colour);
                deleteView.changeColour(colour);
                logoutView.changeColour(colour);


            } else if (selectColourState.getColourError() != null) {
                JOptionPane.showMessageDialog(this, selectColourState.getColourError());
            }
        }

    }

//    private String convertColorToColourName(Color color) {
//        if (color.equals(Color.WHITE)) {
//            return "White";
//        } else if (color.equals(Color.BLUE)) {
//            return "Blue";
//        } else if (color.equals(Color.CYAN)) {
//            return "Cyan";
//        } else if (color.equals(Color.DARK_GRAY)) {
//            return "Dark Gray";
//        } else if (color.equals(Color.GRAY)) {
//            return "Gray";
//        } else if (color.equals(Color.GREEN)) {
//            return "Green";
//        } else if (color.equals(Color.LIGHT_GRAY)) {
//            return "Light Gray";
//        } else if (color.equals(Color.MAGENTA)) {
//            return "Magenta";
//        } else if (color.equals(Color.ORANGE)) {
//            return "Orange";
//        } else if (color.equals(Color.PINK)) {
//            return "Pink";
//        } else if (color.equals(Color.RED)) {
//            return "Red";
//        } else if (color.equals(Color.BLACK)) {
//            return "Black";
//        } else if (color.equals(Color.YELLOW)) {
//            return "Yellow";
//        } else {
//            return "White";
//        }
//    }

}
