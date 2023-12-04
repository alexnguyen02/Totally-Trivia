package use_case.select_colour;

public interface SelectColourOutputBoundary {
    void prepareSuccessView(SelectColourOutputData selectColourOutputData);

    void prepareFailView(String error);
}
