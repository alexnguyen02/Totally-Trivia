package interface_adaptors.delete;

public class DeleteState {
    private String usernames;

    public DeleteState(String usernames) {
        this.usernames = usernames;
    }

    public DeleteState() {}
    public String getUsernames() {
        return usernames;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }
}
