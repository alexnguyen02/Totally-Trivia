package use_case.select_colour;
import java.awt.Color;


public class SelectColourInteractor implements SelectColourInputBoundary{

    final SelectColourDataAccessInterface selectColourDataAccessObject;
    final SelectColourOutputBoundary selectColourPresenter;

    public SelectColourInteractor(SelectColourDataAccessInterface selectColourDataAccessObject,
                                  SelectColourOutputBoundary selectColourPresenter) {
        this.selectColourDataAccessObject = selectColourDataAccessObject;
        this.selectColourPresenter = selectColourPresenter;
    }

    public void execute(SelectColourInputData selectColourInputData) {
        Color selectedColour = selectColourInputData.getColour();
        SelectColourOutputData selectColourOutputData = new SelectColourOutputData(selectColourDataAccessObject.changeColour(selectedColour));
        selectColourPresenter.prepareView(selectColourOutputData);

    }
}
