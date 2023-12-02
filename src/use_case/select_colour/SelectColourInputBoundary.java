package use_case.select_colour;


import entity.ColourPackage;

public interface SelectColourInputBoundary {
    void execute(SelectColourInputData selectColourInputData, ColourPackage colourPackage);
}