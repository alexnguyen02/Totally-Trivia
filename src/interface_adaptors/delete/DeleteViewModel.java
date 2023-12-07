package interface_adaptors.delete;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteViewModel extends ViewModel {
    public static final String DELETE_BUTTON_LABEL = "Delete account";

    private DeleteState state = new DeleteState();


    public DeleteViewModel() {
        super("Delete account");
    }

    public void setState(DeleteState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

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
