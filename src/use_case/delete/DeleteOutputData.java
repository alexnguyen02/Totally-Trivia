package use_case.delete;

// TODO Complete me

import java.util.ArrayList;

public class DeleteOutputData {
    private String usernames;
    public DeleteOutputData(String usernames) {
        this.usernames = usernames;
    }


    public String getUsernames() {
        if (usernames.equals("User not found")){return "User not found";};
        return "You are successful delete account: " + usernames;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }
}
