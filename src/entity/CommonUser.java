package entity;

import java.awt.*;
import java.time.LocalDateTime;

class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private Integer points;
    private Color colourScheme;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, LocalDateTime creationTime, Integer points, Color colourScheme) {
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

    public Color getColourScheme() { return colourScheme; }

    public void setPoints(Integer points) { this.points = points; }

    public void setColourScheme(Color colour) { this.colourScheme = colour; }
}
