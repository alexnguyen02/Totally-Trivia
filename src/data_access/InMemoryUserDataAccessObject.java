package data_access;

import entity.User;
import use_case.game_over.GameOverUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.select_colour.SelectColourUserDataAccessInterface;
import use_case.signup.*;

import java.util.HashMap;
import java.util.Map;

    public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, SelectColourUserDataAccessInterface, GameOverUserDataAccessInterface {
  
    private final Map<String, User> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * @param user the data to save
     */
    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    public void changePoints(String userId, Integer points) {
        User user = users.get(userId);
        user.setPoints(user.getPoints() + points);
    }
        public void changePoints(String userId, Integer points) {
            User user = users.get(userId);
            user.setPoints(user.getPoints() + points);
        }
    public void changeColourScheme(String username, String colourScheme) {

        User user = users.get(username);
        if (user != null) {
            user.setColourScheme(colourScheme);
        }
    }
}

