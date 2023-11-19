package src.interface_adaptors.delete;

public class DeleteState {
    private String username;

    public DeleteState(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
