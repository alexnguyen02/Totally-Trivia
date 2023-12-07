package interface_adaptors.select_colour;

import use_case.select_colour.SelectColourInputBoundary;
import use_case.select_colour.SelectColourInputData;


import java.awt.*;

public class SelectColourController {
    final SelectColourInputBoundary selectColourInteractor;

    public SelectColourController(SelectColourInputBoundary  selectColourInteractor){
        this.selectColourInteractor = selectColourInteractor;
    }

    // Calls interactor's execute method, passing in the user's selected colour (in string format) as an argument
    public void execute(String colour){
        SelectColourInputData selectColourInputData = new SelectColourInputData(colour);
        selectColourInteractor.execute(selectColourInputData);
    }
}
