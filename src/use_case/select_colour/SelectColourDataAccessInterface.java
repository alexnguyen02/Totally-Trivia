package use_case.select_colour;

import entity.ColourPackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface SelectColourDataAccessInterface {
    boolean colourUnlocked(Color colour, ArrayList<Color> possibleColours);
}
