package interface_adaptors.delete;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class DeleteViewModel extends ViewModel {
    public static final String DEELETE_BUTTON_LABEL = "delete";

    private DeleteState state = new DeleteState(getState().getUsername());


    public DeleteViewModel() {
        super("delete user");
    }

    public void setState(DeleteState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public DeleteState getState() {
        return state;
    }


}
