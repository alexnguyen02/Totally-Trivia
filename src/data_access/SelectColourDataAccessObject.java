package data_access;

import java.awt.*;
import java.util.ArrayList;

import use_case.select_colour.SelectColourDataAccessInterface;

public class SelectColourDataAccessObject implements SelectColourDataAccessInterface {
    private final Color selectedColour;

    public SelectColourDataAccessObject(Color selectedColour) {
        this.selectedColour = selectedColour;

    }

    @Override
    public Color changeColour(Color selectedColour) {
//        this.getContentPane().setBackground(colour);
        return selectedColour;
    }

}
