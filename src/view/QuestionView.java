package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adaptors.question.QuestionController;
import interface_adaptors.question.QuestionState;
import interface_adaptors.question.QuestionViewModel;
import use_case.signup.SignupOutputBoundary;

public class QuestionView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "question";

    private final QuestionViewModel questionViewModel;
    private final QuestionController questionController;

    private final JLabel question;
    private final JButton answer1;
    private final JButton answer2;
    private final JButton answer3;
    private final JButton answer4;

    public QuestionView(QuestionViewModel questionViewModel, QuestionController controller) {

        this.questionController = controller;
        this.questionViewModel = questionViewModel;
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
                        questionController.execute(answer1.getText());
                        QuestionState currentState = questionViewModel.getState();
                        rightOrWrong(currentState.getQuestionCorrect());
                    }
                }
        );

        answer2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        questionController.execute(answer2.getText());
                        QuestionState currentState = questionViewModel.getState();
                        rightOrWrong(currentState.getQuestionCorrect());
                    }
                }
        );

        answer3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        questionController.execute(answer3.getText());
                        QuestionState currentState = questionViewModel.getState();
                        rightOrWrong(currentState.getQuestionCorrect());
                    }
                }
        );

        answer4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        questionController.execute(answer4.getText());
                        QuestionState currentState = questionViewModel.getState();
                        rightOrWrong(currentState.getQuestionCorrect());
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
        if (correctness) {
            JOptionPane.showMessageDialog(this, "That is correct :)");
        } else {
            JOptionPane.showMessageDialog(this, "That answer is incorrect :(");
        }
        questionViewModel.updateViewModel();
        updateView();
        System.out.println(questionViewModel.getState().getQuestionNum());
    }
}