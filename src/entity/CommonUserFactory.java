package entity;

import java.awt.*;
import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */

    @Override
    public User create(String name, String password, LocalDateTime ltd, Integer points, Color colourScheme) {
        return new CommonUser(name, password, ltd, points, colourScheme);
    }
}
