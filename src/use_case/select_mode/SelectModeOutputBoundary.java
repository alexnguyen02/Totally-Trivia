package use_case.select_mode;

public interface SelectModeOutputBoundary {

    public void prepareSelectModeSuccessView(SelectModeOutputData selectModeOutputData);

    public void prepareSelectModeFailView(String error);
}
