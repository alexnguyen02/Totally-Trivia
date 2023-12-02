package use_case.select_colour;
import entity.User;
import use_case.signup.SignupOutputData;
import entity.ColourPackage;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class SelectColourInteractor implements SelectColourInputBoundary{

    final SelectColourDataAccessInterface selectColourDataAccessObject;
    final SelectColourOutputBoundary selectColourPresenter;

    public SelectColourInteractor(SelectColourDataAccessInterface selectColourDataAccessObject,
                                  SelectColourOutputBoundary selectColourPresenter) {
        this.selectColourDataAccessObject = selectColourDataAccessObject;
        this.selectColourPresenter = selectColourPresenter;
    }

    public void execute(SelectColourInputData selectColourInputData, ColourPackage colourPackage) {
        Color selectedColour = selectColourInputData.getColour();
        ArrayList<Color> unlockedColours = colourPackage.getUnlockedColours();

        if (!selectColourDataAccessObject.colourUnlocked(selectedColour, unlockedColours)) {
            selectColourPresenter.prepareFailView("This colour has not been unlocked yet.");
        } else {
            SelectColourOutputData selectColourOutputData = new SelectColourOutputData(selectedColour);
            selectColourPresenter.prepareSuccessView(selectColourOutputData);
        }
    }
}
