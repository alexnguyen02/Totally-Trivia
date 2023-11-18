package src.view;

import src.interface_adaptors.select_mode.SelectModeController;
import src.interface_adaptors.select_mode.SelectModeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SelectModeView extends JPanel implements ActionListener, PropertyChangeListener {

    public String viewName = "select mode";

    // View Model
    private final SelectModeViewModel selectModeViewModel;

    // Controller
    private final SelectModeController selectModeController;

    // Dropdown menus of category and difficulty level, and text input field for number of questions
    private final String[] categoryList = new String[]{"Any category", "General knowledge", "Books", "Film",
            "Music", "Musicals & Theatres", "Television", "Video games", "Board games", "Science & Nature", "Computers",
            "Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Arts", "Celebrities", "Animals",
            "Vehicles", "Comics", "Gadgets", "Japanese Anime & Manga", "Cartoon & Animations"};
    private final JComboBox<String> categoryDropDown = new JComboBox<>(categoryList);
    private final JComboBox<String> difficultyLevelDropDown = new JComboBox<>(new String[]{"Any difficulty level","Easy", "Medium", "Hard"});
    private final JTextField numOfQuestionInputField= new JTextField(15);


    // Start (game) button
    // private final JButton start;

    // Reset all the choices button
    // In reset mode: any category, any difficulty level and 10 number of questions.
    // private final JButton reset;

    public SelectModeView(SelectModeViewModel selectModeViewModel, SelectModeController selectModeController){
        this.selectModeViewModel = selectModeViewModel;
        this.selectModeController = selectModeController;
        this.selectModeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Start screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelDropDownPanel categoryInfo = new LabelDropDownPanel(new JLabel("Category"), categoryDropDown);
        LabelDropDownPanel difficultyInfo = new LabelDropDownPanel(new JLabel("Difficulty"), difficultyLevelDropDown);
        LabelTextPanel numOfQuestionInfo = new LabelTextPanel(new JLabel("Number of Questions"), numOfQuestionInputField);

        JPanel buttons = new JPanel();
        // start = new JButton(selectModeViewModel.START_BUTTON_LABEL);
        // buttons.add(start);
        // reset = new JButton(selectModeViewModel.RESET_BUTTON_LABEL);
        //  buttons.add(reset);

        // start.addActionListener(

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
