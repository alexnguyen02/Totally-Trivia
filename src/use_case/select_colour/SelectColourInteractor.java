package use_case.select_colour;
import entity.User;
import use_case.signup.SignupOutputData;

//import java.awt.Color;
import java.time.LocalDateTime;
//import java.util.ArrayList;

import data_access.FileUserDataAccessObject;


public class SelectColourInteractor implements SelectColourInputBoundary{
    final SelectColourOutputBoundary selectColourPresenter;
    final User user;
    final FileUserDataAccessObject fileUserDataAccessObject;

    public SelectColourInteractor(SelectColourOutputBoundary selectColourPresenter, User user,
                                  FileUserDataAccessObject fileUserDataAccessObject) {
        this.selectColourPresenter = selectColourPresenter;
        this.user = user;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
    }

    public void execute(SelectColourInputData selectColourInputData) {
        String selectedColour = selectColourInputData.getColour();
        Integer points = user.getPoints();
        Boolean success = false;

        if (selectedColour.equals("White") && points >= 0){
            success = true;
        } else if (selectedColour.equals("Blue") && points >= 5) {
            success = true;
        } else if (selectedColour.equals("Cyan") && points >= 10) {
            success = true;
        } else if (selectedColour.equals("Dark Gray") && points >= 15) {
            success = true;
        } else if (selectedColour.equals("Gray") && points >= 20) {
            success = true;
        } else if (selectedColour.equals("Green") && points >= 25) {
            success = true;
        } else if (selectedColour.equals("Light Gray") && points >= 30) {
            success = true;
        } else if (selectedColour.equals("Magenta") && points >= 35) {
            success = true;
        } else if (selectedColour.equals("Orange") && points >= 40) {
            success = true;
        } else if (selectedColour.equals("Pink") && points >= 45) {
            success = true;
        } else if (selectedColour.equals("Red") && points >= 50) {
            success = true;
        } else if (selectedColour.equals("Black") && points >= 55) {
            success = true;
        } else if (selectedColour.equals("Yellow") && points >= 60) {
            success = true;
        }

        if (success) {
            user.setColourScheme(selectedColour);
            fileUserDataAccessObject.changeColourScheme(user.getName(), selectedColour);

            SelectColourOutputData selectColourOutputData = new SelectColourOutputData(selectedColour);
            selectColourPresenter.prepareSuccessView(selectColourOutputData);
        } else {
            selectColourPresenter.prepareFailView("This colour has not been unlocked yet.");
        }
    }
}
