package view;


import interface_adaptors.game_over.GameOverController;
import interface_adaptors.game_over.GameOverViewModel;
import interface_adaptors.question.QuestionState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameOverView extends JPanel implements ActionListener, PropertyChangeListener  {
    public final String viewName = "game over";

    public final GameOverViewModel gameOverViewModel;
    public final GameOverController gameOverController;
    private final JLabel title;
    public final JLabel points_earned;
    public final JLabel correct_answers;
    private final JButton play_again;
    private final JButton main_menu;

    public GameOverView(GameOverViewModel gameOverViewModel, GameOverController gameOverController) {
        this.gameOverController = gameOverController;
        this.gameOverViewModel = gameOverViewModel;
        gameOverViewModel.addPropertyChangeListener(this);

        this.title = new JLabel(gameOverViewModel.TITLE_LABEL);
        this.points_earned = new JLabel(gameOverViewModel.POINTS_EARNED + gameOverViewModel.getState().getPointsEarned());
        this.correct_answers = new JLabel(gameOverViewModel.CORRECT_ANSWERS +
                gameOverViewModel.getState().getCorrectNum() + '/' + gameOverViewModel.getState().getTotalNum());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        points_earned.setAlignmentX(Component.CENTER_ALIGNMENT);
        correct_answers.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box buttons = Box.createHorizontalBox();
        Box spacing = Box.createVerticalBox();
        spacing.add(Box.createVerticalStrut(30));

        play_again = new JButton(gameOverViewModel.PLAY_AGAIN_BUTTON_LABEL);
        buttons.add(play_again);
        main_menu = new JButton(gameOverViewModel.MAIN_MENU_BUTTON_LABEL);
        buttons.add(main_menu);

        play_again.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameOverController.execute("select mode", gameOverViewModel.getState().getPointsEarned());
                    }
                }
        );

        main_menu.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameOverController.execute("main screen", gameOverViewModel.getState().getPointsEarned());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(points_earned);
        this.add(correct_answers);
        this.add(spacing);
        this.add(buttons);

    }

    public void updateView() {
        this.points_earned.setText(gameOverViewModel.POINTS_EARNED + gameOverViewModel.getState().getPointsEarned());
        this.correct_answers.setText(gameOverViewModel.CORRECT_ANSWERS +
                gameOverViewModel.getState().getCorrectNum() + '/' + gameOverViewModel.getState().getTotalNum());
    }

    public void changeColour(Color colour) {
        this.setBackground(colour);
    }

    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) { updateView(); }
}
