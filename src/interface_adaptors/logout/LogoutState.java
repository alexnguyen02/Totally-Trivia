package interface_adaptors.logout;

public class LogoutState {
    private String username;
    private boolean successfulLogout;

    public LogoutState(String username, boolean successfulLogout) {

        this.username = username;
        this.successfulLogout = successfulLogout;
    }

    public LogoutState(String username) {

        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSuccessfulLogout() {
        return successfulLogout;
    }
}
