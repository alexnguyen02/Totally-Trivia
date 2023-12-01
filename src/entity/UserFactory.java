package entity;

import java.awt.*;
import java.time.LocalDateTime;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(String name, String password, LocalDateTime ltd, Integer points, Color colourScheme);
}
