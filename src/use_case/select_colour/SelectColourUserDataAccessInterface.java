package use_case.select_colour;

import entity.User;

public interface SelectColourUserDataAccessInterface {
    void save(User user);

    void changeColourScheme(String userId, String colour);
}
