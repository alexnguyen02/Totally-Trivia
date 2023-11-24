package interface_adaptors.question;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import interface_adaptors.ViewModel;

public class QuestionViewModel extends ViewModel {

    private String viewName;
    public String QUESTION_TITLE_LABEL;
    public String FIRST_ANSWER_BUTTON_LABEL;
    public String SECOND_ANSWER_BUTTON_LABEL;
    public String THIRD_ANSWER_BUTTON_LABEL;
    public String FOURTH_ANSWER_BUTTON_LABEL;
    private QuestionState state = new QuestionState();

    public QuestionViewModel() {
        super("question");
        this.QUESTION_TITLE_LABEL = "Who is the mother of the Gracchi?";
        this.FIRST_ANSWER_BUTTON_LABEL = "Cornelia";
        this.SECOND_ANSWER_BUTTON_LABEL = "Livia";
        this.THIRD_ANSWER_BUTTON_LABEL = "Drusilla";
        this.FOURTH_ANSWER_BUTTON_LABEL = "Aggripina";
    }
    public String getViewName() {return this.viewName;}
    public void setState(QuestionState state) {
        this.state = state;
    }
    public QuestionState getState() {
        return this.state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state); }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
