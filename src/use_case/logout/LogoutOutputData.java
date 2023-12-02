package use_case.logout;

public class LogoutOutputData {
    private final boolean success;
    private final String username;

    public LogoutOutputData(boolean success, String username) {
        this.success = success;
        this.username = username;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getUsername() {
        return username;
    }

}