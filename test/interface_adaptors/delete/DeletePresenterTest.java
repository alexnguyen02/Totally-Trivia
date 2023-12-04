package interface_adaptors.delete;

import static org.junit.jupiter.api.Assertions.*;

import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeletePresenterTest {

    @Test
    void testPrepareSuccessView() {
        // Create a DeleteViewModel for testing
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Create a DeletePresenter with the DeleteViewModel
        DeletePresenter deletePresenter = new DeletePresenter(viewManagerModel, deleteViewModel);

        // Create test data for the response
        use_case.delete.DeleteOutputData testOutputData = new use_case.delete.DeleteOutputData("testUser");
        testOutputData.setUsernames("testUser");

        // Call prepareSuccessView with the test data
        deletePresenter.prepareSuccessView(testOutputData);

        // Verify that the DeleteViewModel is updated correctly
        DeleteState deleteState = deleteViewModel.getState();
        assertEquals("You are successful delete account: testUser", deleteState.getUsernames());
    }

    @Test
    void testPrepareFailView() {
        // Create a DeleteViewModel for testing
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Create a DeletePresenter with the DeleteViewModel
        DeletePresenter deletePresenter = new DeletePresenter(viewManagerModel, deleteViewModel);

        // Call prepareFailView with a test error message
        deletePresenter.prepareFailView("Test error message");

        // Verify that the DeleteViewModel is not updated (assuming no changes in prepareFailView)
        DeleteState deleteState = deleteViewModel.getState();
        assertNull(deleteState.getUsernames());
    }
}