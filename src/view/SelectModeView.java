package view;

import interface_adaptors.select_mode.SelectModeController;
import interface_adaptors.select_mode.SelectModeState;
import interface_adaptors.select_mode.SelectModeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static interface_adaptors.select_mode.SelectModeViewModel.*;
import static interface_adaptors.select_mode.SelectModeViewModel.NUM_QUESTIONS_LABEL;

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
    private final JComboBox<String> categoryDropdown = new JComboBox<>(categoryList);
    private final JComboBox<String> difficultyLevelDropdown = new JComboBox<>(new String[]{"Any difficulty level","Easy", "Medium", "Hard"});

    // The array of integer {2,4,6} is for testing purpose only; The actual array of integer is {10,20,30}
    private final JComboBox<Integer> numOfQuestionDropdown = new JComboBox<>(new Integer[]{2, 4, 6}); //


    // Start (game) button
    private final JButton start;

    // Reset all the choices button
    // In reset mode: any category, any difficulty level and 10 number of questions.
    private final JButton reset;

    public SelectModeView(SelectModeViewModel selectModeViewModel, SelectModeController selectModeController){
        this.selectModeViewModel = selectModeViewModel;
        this.selectModeController = selectModeController;
        this.selectModeViewModel.addPropertyChangeListener(this);

        selectModeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelDropDownPanel categoryInfo = new LabelDropDownPanel(new JLabel(CATEGORY_LABEL), categoryDropdown);
        LabelDropDownPanel difficultyInfo = new LabelDropDownPanel(new JLabel(DIFFICULTY_LABEL), difficultyLevelDropdown);
        LabelDropDownPanel numOfQuestionInfo = new LabelDropDownPanel(new JLabel(NUM_QUESTIONS_LABEL), numOfQuestionDropdown);

        JPanel buttons = new JPanel();
        start = new JButton(SelectModeViewModel.START_BUTTON_LABEL);
        buttons.add(start);
        reset = new JButton(SelectModeViewModel.RESET_BUTTON_LABEL);
        buttons.add(reset);

        start.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(start)) {
                            SelectModeState currentState = selectModeViewModel.getState();
                            System.out.println(currentState.getCategory());
                            System.out.println(currentState.getDifficultyLevel());
                            System.out.println(currentState.getNumOfQuestions());

                            selectModeController.execute(
                                    currentState.getCategory(),
                                    currentState.getDifficultyLevel(),
                                    currentState.getNumOfQuestions()
                            );
                        }
                    }
                }
        );

        reset.addActionListener(this);

        categoryDropdown.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SelectModeState currentState = selectModeViewModel.getState();
                        String category = categoryDropdown.getSelectedItem().toString();
                        currentState.setCategory(category);
                        selectModeViewModel.setState(currentState);
                    }
                }
        );

        difficultyLevelDropdown.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SelectModeState currentState = selectModeViewModel.getState();
                        String difficultyLevel = difficultyLevelDropdown.getSelectedItem().toString();
                        currentState.setDifficultyLevel(difficultyLevel);
                        selectModeViewModel.setState(currentState);
                    }
                }
        );

        numOfQuestionDropdown.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SelectModeState currentState = selectModeViewModel.getState();
                        Integer numOfQuestions = Integer.parseInt(numOfQuestionDropdown.getSelectedItem().toString());
                        currentState.setNumOfQuestions(numOfQuestions);
                        selectModeViewModel.setState(currentState);
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(categoryInfo);
        this.add(difficultyInfo);
        this.add(numOfQuestionInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Reset not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SelectModeState){
            SelectModeState selectModeState = (SelectModeState) evt.getNewValue();

            if (selectModeState.getOutputQuestions() != null){
                JOptionPane.showMessageDialog(this, selectModeState.toString());
            }
        }

    }
}
