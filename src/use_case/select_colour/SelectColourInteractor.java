package use_case.select_colour;
import entity.User;


public class SelectColourInteractor implements SelectColourInputBoundary{
    final SelectColourOutputBoundary selectColourPresenter;
    final User user;
    final SelectColourUserDataAccessInterface selectColourUserDataAccessInterface;

    public SelectColourInteractor(SelectColourOutputBoundary selectColourPresenter, User user,
                                  SelectColourUserDataAccessInterface selectColourUserDataAccessInterface) {
        this.selectColourPresenter = selectColourPresenter;
        this.user = user;
        this.selectColourUserDataAccessInterface = selectColourUserDataAccessInterface;
    }

    public void execute(SelectColourInputData selectColourInputData) {
        String selectedColour = selectColourInputData.getColour();
        Integer points = user.getPoints();
        Boolean success = false;

        if ((selectedColour.equals("White") && points >= 0) ||
            (selectedColour.equals("Blue") && points >= 5) ||
            (selectedColour.equals("Cyan") && points >= 10) ||
            (selectedColour.equals("Dark Gray") && points >= 15) ||
            (selectedColour.equals("Gray") && points >= 20) ||
            (selectedColour.equals("Green") && points >= 25) ||
            (selectedColour.equals("Light Gray") && points >= 30) ||
            (selectedColour.equals("Magenta") && points >= 35) ||
            (selectedColour.equals("Orange") && points >= 40) ||
            (selectedColour.equals("Pink") && points >= 45) ||
            (selectedColour.equals("Red") && points >= 50) ||
            (selectedColour.equals("Black") && points >= 55) ||
            (selectedColour.equals("Yellow") && points >= 60)) {

            success = true;
        }

        if (success) {
            user.setColourScheme(selectedColour);
            selectColourUserDataAccessInterface.changeColourScheme(user.getName(), selectedColour);

            SelectColourOutputData selectColourOutputData = new SelectColourOutputData(selectedColour);
            selectColourPresenter.prepareSuccessView(selectColourOutputData);
        } else {
            selectColourPresenter.prepareFailView("This colour has not been unlocked yet.");
        }
    }
}
