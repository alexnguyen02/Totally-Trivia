package entity;

import java.awt.*;
import java.time.LocalDateTime;

public interface User {

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

    Integer getPoints();

    Color getColourScheme();

    void setPoints(Integer points);

    void setColourScheme(Color colour);
}