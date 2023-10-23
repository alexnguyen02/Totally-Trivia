package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import src.interface_adaptors.QuestionViewModel;

public class QuestionView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "question";

    private final QuestionViewModel questionViewModel;
    //private final QuestionController questionController;

    private final JButton answer1;
    private final JButton answer2;
    private final JButton answer3;
    private final JButton answer4;

    public QuestionView(QuestionViewModel questionViewModel) {

        //this.signupController = controller;
        this.questionViewModel = questionViewModel;
        //this.clearController = clearController;
        //this.clearViewModel = clearViewModel;
        questionViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(QuestionViewModel.QUESTION_TITLE_LABEL);
        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4, 4));

        //contentPane.setBorder(someB);
        //contentPane.add(someComponent, BorderLayout.CENTER);
        //contentPane.add(anotherComponent, BorderLayout.PAGE_END);
        //topLevelContainer.setContentPane(contentPane);

        answer1 = new JButton(QuestionViewModel.FIRST_ANSWER_BUTTON_LABEL);
        answer1.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        answer1.setSize(new Dimension(10,10));
        buttons.add(answer1);
        answer2 = new JButton(QuestionViewModel.SECOND_ANSWER_BUTTON_LABEL);
        answer2.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        answer2.setSize(new Dimension(10,10));
        buttons.add(answer2);
        answer3 = new JButton(QuestionViewModel.THIRD_ANSWER_BUTTON_LABEL);
        answer3.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        answer3.setSize(new Dimension(10,10));
        buttons.add(answer3);
        answer4 = new JButton(QuestionViewModel.FOURTH_ANSWER_BUTTON_LABEL);
        answer4.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        answer4.setSize(new Dimension(10,10));
        buttons.add(answer4);

        answer1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //questionController.execute(answer1.getText());
                        rightOrWrong(true);
                    }
                }
        );

        answer2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //questionController.execute(answer2.getText());
                        rightOrWrong(false);
                    }
                }
        );

        answer3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //questionController.execute(answer3.getText());
                        rightOrWrong(false);
                    }
                }
        );

        answer4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //questionController.execute(answer4.getText());
                        rightOrWrong(false);
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
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
    }

}