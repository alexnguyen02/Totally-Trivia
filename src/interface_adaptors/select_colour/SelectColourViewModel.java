package interface_adaptors.select_colour;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import interface_adaptors.ViewModel;

public class SelectColourViewModel extends ViewModel{

    private String colour;

    private static SelectColourState state;

    public SelectColourViewModel() {
        super("select colour");
        this.state = new SelectColourState(colour);
    }

    public void setState(SelectColourState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public static SelectColourState getState() {
        return state;
    }
}
