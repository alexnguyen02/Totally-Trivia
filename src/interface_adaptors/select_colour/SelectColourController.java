package interface_adaptors.select_colour;

import use_case.select_colour.SelectColourInputBoundary;
import use_case.select_colour.SelectColourInputData;


import java.awt.*;

public class SelectColourController {
    final SelectColourInputBoundary selectColourInteractor;

    public SelectColourController(SelectColourInputBoundary  selectColourInteractor){
        this.selectColourInteractor = selectColourInteractor;
    }

    public void execute(Color colour){
        SelectColourInputData selectColourInputData = new SelectColourInputData(colour);
        selectColourInteractor.execute(selectColourInputData);
    }
}
