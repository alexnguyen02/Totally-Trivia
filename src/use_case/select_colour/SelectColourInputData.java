package use_case.select_colour;

import java.awt.Color;

public class SelectColourInputData {
    private Color colour;

    public SelectColourInputData(Color colour) {
        this.colour = colour;
    }

    Color getColour() {
        return colour;
    }
}
