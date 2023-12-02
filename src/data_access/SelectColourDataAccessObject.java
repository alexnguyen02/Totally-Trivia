package data_access;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import entity.User;
import entity.UserFactory;

import entity.ColourPackage;

import use_case.select_colour.SelectColourDataAccessInterface;

public class SelectColourDataAccessObject implements SelectColourDataAccessInterface {
    private final Color selectedColour;

    private final ArrayList<Color> possibleColours;

    public SelectColourDataAccessObject(Color selectedColour, ArrayList<Color> possibleColours)  {
        this.selectedColour = selectedColour;
        this.possibleColours = possibleColours;

    }

    @Override
    public boolean colourUnlocked(Color selectedColour, ArrayList<Color> possibleColours) {
        return possibleColours.contains(selectedColour);
    }

}