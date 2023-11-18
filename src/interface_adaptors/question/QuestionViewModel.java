package src.interface_adaptors.question;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import src.interface_adaptors.ViewModel;

public class QuestionViewModel extends ViewModel {

    private String viewName;
    public static final String QUESTION_TITLE_LABEL = "Which Roman woman was the mother of Tiberius and Gaius Gracchus?";
    public static final String FIRST_ANSWER_BUTTON_LABEL = "Cornelia";
    public static final String SECOND_ANSWER_BUTTON_LABEL = "Livia Drusilla";
    public static final String THIRD_ANSWER_BUTTON_LABEL = "Fulvia";
    public static final String FOURTH_ANSWER_BUTTON_LABEL = "Antonia the Elder";

    public QuestionViewModel() { super("question"); }
    public String getViewName() {
        return this.viewName;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        //support.firePropertyChange();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
