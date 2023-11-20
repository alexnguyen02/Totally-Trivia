package src.interface_adaptors.select_mode;

import src.interface_adaptors.ViewModel;
import src.view.SelectModeView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class SelectModeViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Custom your trivia";

    public static final String CATEGORY_LABEL = "Category";
    public static final String DIFFICULTY_LABEL = "Difficulty level";
    public static final String NUM_QUESTIONS_LABEL = "Number of questions";

    public static final String START_BUTTON_LABEL = "Start";
    public static final String RESET_BUTTON_LABEL = "Reset";

    private ArrayList<String> outputQuestions;
    private static SelectModeState state;

    public SelectModeViewModel(){
        super("selectMode");
        state = new SelectModeState(outputQuestions);
    }

    public void setState(SelectModeState state){
        SelectModeViewModel.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SelectModeState getState(){ return state;}
}
