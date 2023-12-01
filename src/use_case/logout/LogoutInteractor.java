package use_case.logout;

public class LogoutInteractor {
    public void execute(LogoutInputData inputData, LogoutOutputBoundary outputBoundary) {
        // Perform any necessary validation or business logic related to logout
        // Example: Check if the user exists, update the user's authentication status, etc.

        // For the sake of simplicity, let's assume the logout is always successful
        boolean success = true;
        String message = "Logout successful";

        // Create an instance of LogoutOutputData with the result
        LogoutOutputData outputData = new LogoutOutputData(success, message);

        // Pass the output data to the output boundary for presentation
        outputBoundary.prepareSuccessView(outputData);
    }
}