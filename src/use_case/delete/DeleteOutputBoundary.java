package use_case.delete;

// TODO Complete me

public interface DeleteOutputBoundary {
    void prepareSuccessView(DeleteOutputData user);

    void prepareFailView(String error);
}
