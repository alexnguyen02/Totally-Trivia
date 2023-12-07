package view;

import interface_adaptors.ViewManagerModel;
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
    private final ViewManagerModel viewManagerModel;

    // Dropdown menus of category and difficulty level, and text input field for number of questions
    private final String[] categoryList = new String[]{"Select","Any category", "General knowledge", "Books", "Film",
            "Music", "Musicals & Theatres", "Television", "Video games", "Board games", "Science & Nature", "Computers",
            "Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Arts", "Celebrities", "Animals",
            "Vehicles", "Comics", "Gadgets", "Japanese Anime & Manga", "Cartoon & Animations"};
    private final JComboBox<String> categoryDropdown = new JComboBox<>(categoryList);
    private final JComboBox<String> difficultyLevelDropdown = new JComboBox<>(new String[]{"Select","Any difficulty level","Easy", "Medium", "Hard"});

    // The array of integer {2,4,6} is for testing purpose only; The actual array of integer is {10,20,30}
    private final JComboBox<String> numOfQuestionDropdown = new JComboBox<>(new String[]{"Select","5", "10", "20", "30"}); //


    // Start (game) button
    private final JButton start;

    // Reset all the choices button
    // In reset mode: any category, any difficulty level and 10 number of questions.
    private final JButton back;
    private final LabelDropDownPanel categoryInfo;
    private final LabelDropDownPanel difficultyInfo;
    private final LabelDropDownPanel numOfQuestionInfo;

    public String getTitle(){
        return TITLE_LABEL;
    }

    public String getCategoryLabel(){
        return CATEGORY_LABEL;
    }

    public String getDifficultyLevelLabel(){
        return DIFFICULTY_LABEL;
    }

    public String getNumQuestionsLabel(){
        return NUM_QUESTIONS_LABEL;
    }

    public SelectModeView(SelectModeViewModel selectModeViewModel, SelectModeController selectModeController,
                          ViewManagerModel viewManagerModel){
        this.selectModeViewModel = selectModeViewModel;
        this.selectModeController = selectModeController;
        this.selectModeViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        selectModeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        categoryInfo = new LabelDropDownPanel(new JLabel(CATEGORY_LABEL), categoryDropdown);
        difficultyInfo = new LabelDropDownPanel(new JLabel(DIFFICULTY_LABEL), difficultyLevelDropdown);
        numOfQuestionInfo = new LabelDropDownPanel(new JLabel(NUM_QUESTIONS_LABEL), numOfQuestionDropdown);

        Box buttons = Box.createHorizontalBox();
        start = new JButton(SelectModeViewModel.START_BUTTON_LABEL);
        buttons.add(start);
        back = new JButton(SelectModeViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        start.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(start)) {
                            SelectModeState currentState = selectModeViewModel.getState();
                            selectModeController.execute(
                                    currentState.getCategory(),
                                    currentState.getDifficultyLevel(),
                                    currentState.getNumOfQuestionsString()
                            );
                        }
                    }
                }
        );

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewManagerModel.setActiveView("main screen");
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

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
                        String numOfQuestions = numOfQuestionDropdown.getSelectedItem().toString();
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

    public void changeColour(Color colour) {

        this.setBackground(colour);
        this.categoryInfo.setBackground(colour);
        this.difficultyInfo.setBackground(colour);
        this.numOfQuestionInfo.setBackground(colour);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SelectModeState state = (SelectModeState) evt.getNewValue();
        if (state.getNotSelectedError() != null) {
            JOptionPane.showMessageDialog(this, state.getNotSelectedError());
        }
    }

}
