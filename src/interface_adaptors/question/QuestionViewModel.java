package interface_adaptors.question;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import entity.Question;
import interface_adaptors.ViewModel;

public class QuestionViewModel extends ViewModel {

    private String viewName;
    public String QUESTION_TITLE_LABEL;
    public String FIRST_ANSWER_BUTTON_LABEL;
    public String SECOND_ANSWER_BUTTON_LABEL;
    public String THIRD_ANSWER_BUTTON_LABEL;
    public String FOURTH_ANSWER_BUTTON_LABEL;
    private QuestionState state = new QuestionState();

    public QuestionViewModel() { super("question"); }
    public void updateViewModel(Question newQuestion) {
        this.QUESTION_TITLE_LABEL = newQuestion.getContent();
        ArrayList<String> answers = newQuestion.getAnswerPackage().getPossibleAnswers();
        this.FIRST_ANSWER_BUTTON_LABEL = answers.get(0);
        this.SECOND_ANSWER_BUTTON_LABEL = answers.get(1);
        this.THIRD_ANSWER_BUTTON_LABEL = answers.get(2);
        this.FOURTH_ANSWER_BUTTON_LABEL = answers.get(3);

    }
    public String getViewName() {return this.viewName;}
    public void setState(QuestionState state) {
        this.state = state;
    }
    public QuestionState getState() { return this.state; }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state); }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void resetViewModel() {
        this.QUESTION_TITLE_LABEL = "";
        this.FIRST_ANSWER_BUTTON_LABEL = "";
        this.SECOND_ANSWER_BUTTON_LABEL = "";
        this.THIRD_ANSWER_BUTTON_LABEL = "";
        this.FOURTH_ANSWER_BUTTON_LABEL = "";
    }
}
