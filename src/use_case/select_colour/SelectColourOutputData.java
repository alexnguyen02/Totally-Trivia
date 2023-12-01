package use_case.select_colour;

import java.awt.Color;
import java.util.List;

public class SelectColourOutputData {

    private Color colour;

    public SelectColourOutputData(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }
}
