package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adaptors.game_over.GameOverState;
import interface_adaptors.game_over.GameOverViewModel;
import interface_adaptors.question.QuestionController;
import interface_adaptors.question.QuestionState;
import interface_adaptors.question.QuestionViewModel;

public class QuestionView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "question";

    private final QuestionViewModel questionViewModel;
    private final GameOverViewModel gameOverViewModel;
    private final QuestionController questionController;

    private final JLabel question;
    private final JButton answer1;
    private final JButton answer2;
    private final JButton answer3;
    private final JButton answer4;

    public QuestionView(QuestionViewModel questionViewModel, QuestionController controller,
                        GameOverViewModel gameOverViewModel) {

        this.questionController = controller;
        this.questionViewModel = questionViewModel;
        this.gameOverViewModel = gameOverViewModel;
        questionViewModel.addPropertyChangeListener(this);

        this.question = new JLabel(questionViewModel.QUESTION_TITLE_LABEL);
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        answer1 = new JButton(questionViewModel.FIRST_ANSWER_BUTTON_LABEL);
        buttons.add(answer1);
        answer2 = new JButton(questionViewModel.SECOND_ANSWER_BUTTON_LABEL);
        buttons.add(answer2);
        answer3 = new JButton(questionViewModel.THIRD_ANSWER_BUTTON_LABEL);
        buttons.add(answer3);
        answer4 = new JButton(questionViewModel.FOURTH_ANSWER_BUTTON_LABEL);
        buttons.add(answer4);

        answer1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuestionState currentState = questionViewModel.getState();
                        questionController.execute(answer2.getText(), currentState.getQuestionNum());
                        QuestionState currentState2 = questionViewModel.getState();
                        rightOrWrong(currentState2.getQuestionCorrect());
                    }
                }
        );

        answer2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuestionState currentState = questionViewModel.getState();
                        questionController.execute(answer2.getText(), currentState.getQuestionNum());
                        QuestionState currentState2 = questionViewModel.getState();
                        rightOrWrong(currentState2.getQuestionCorrect());
                    }
                }
        );

        answer3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuestionState currentState = questionViewModel.getState();
                        questionController.execute(answer2.getText(), currentState.getQuestionNum());
                        QuestionState currentState2 = questionViewModel.getState();
                        rightOrWrong(currentState2.getQuestionCorrect());
                    }
                }
        );

        answer4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuestionState currentState = questionViewModel.getState();
                        questionController.execute(answer2.getText(), currentState.getQuestionNum());
                        QuestionState currentState2 = questionViewModel.getState();
                        rightOrWrong(currentState2.getQuestionCorrect());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(question);
        this.add(buttons);
    }

    public void updateView() {
        question.setText(questionViewModel.QUESTION_TITLE_LABEL);
        answer1.setText(questionViewModel.FIRST_ANSWER_BUTTON_LABEL);
        answer2.setText(questionViewModel.SECOND_ANSWER_BUTTON_LABEL);
        answer3.setText(questionViewModel.THIRD_ANSWER_BUTTON_LABEL);
        answer4.setText(questionViewModel.FOURTH_ANSWER_BUTTON_LABEL);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}

    public void rightOrWrong(Boolean correctness) {
        GameOverState gameOverState = gameOverViewModel.getState();
        if (correctness) {
            JOptionPane.showMessageDialog(this, "That is correct :)");
            gameOverState.setCorrectNum(gameOverState.getCorrectNum() + 1);
            gameOverState.setPointsEarned(gameOverState.getPointsEarned() + 5);
        } else {
            JOptionPane.showMessageDialog(this, "That answer is incorrect :(");
        }
        gameOverState.setTotalNum(gameOverState.getTotalNum() + 1);
        gameOverViewModel.setState(gameOverState);
        QuestionState questionState = questionViewModel.getState();
        questionViewModel.updateViewModel(questionState.getNewQuestion());
        updateView();
    }
}