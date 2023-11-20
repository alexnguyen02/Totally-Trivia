package use_case.delete;


public class DeleteOutputData {
    private String username;
    public DeleteOutputData(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
