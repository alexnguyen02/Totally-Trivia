package entity;

import java.time.LocalDateTime;

public interface User {

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

    Integer getPoints();

    String getColourScheme();

    void setPoints(Integer points);

    void setColourScheme(String colour);

    void copyUser(User user);
}