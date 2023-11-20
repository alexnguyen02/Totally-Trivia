package src.use_case.delete;

public interface DeleteOutputBoundary {
    void prepareSuccessView(DeleteOutputData user);

    void prepareFailView(String error);
}
