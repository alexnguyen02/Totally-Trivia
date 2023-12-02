package entity;

import java.awt.*;
import java.time.LocalDateTime;

class CommonUser implements User {

    private String name;
    private String password;
    private LocalDateTime creationTime;
    private Integer points;
    private String colourScheme;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, LocalDateTime creationTime, Integer points, String colourScheme) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.points = points;
        this.colourScheme = colourScheme;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Integer getPoints() { return points; }

    public String getColourScheme() { return colourScheme; }

    public void setPoints(Integer points) { this.points = points; }

    public void setColourScheme(String colour) { this.colourScheme = colour; }
    public void copyUser(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.creationTime = user.getCreationTime();
        this.points = user.getPoints();
        this.colourScheme = user.getColourScheme();
    }
}
