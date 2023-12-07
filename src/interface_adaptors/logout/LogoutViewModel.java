package interface_adaptors.logout;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class LogoutViewModel extends ViewModel {
    public static final String LOGOUT_BUTTON_LABEL = "Log Out";

    private LogoutState state;


    public LogoutViewModel() {
        super("logout");
    }

    public void setState(LogoutState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LogoutState getState() {
        return state;
    }


}
