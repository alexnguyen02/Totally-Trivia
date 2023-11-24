package use_case.question;

public interface QuestionOutputBoundary {

    void prepareSuccessView(QuestionOutputData questionOutputData);

    void prepareFailView(String error);
}
