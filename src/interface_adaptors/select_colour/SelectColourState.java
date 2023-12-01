package interface_adaptors.select_colour;

import java.util.List;
import java.awt.*;


public class SelectColourState {
    private Color colour;

    public SelectColourState(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }

}
