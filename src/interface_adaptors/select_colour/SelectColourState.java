package interface_adaptors.select_colour;

import java.util.List;
import java.awt.*;


public class SelectColourState {
    private String colour;
    private String colourError = null;

    public SelectColourState(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public String getColourError() {
        return colourError;
    }

    public void setColourError(String colourError) {
        this.colourError = colourError;
    }

}
