package interface_adaptors.delete;

import org.junit.jupiter.api.Test;
import use_case.delete.DeleteInputBoundary;

import static org.junit.Assert.assertEquals;

class DeleteControllerTest {

    private static class DeleteInputBoundaryStub implements DeleteInputBoundary {
        private String executedInput;

        @Override
        public void execute(String input) {
            executedInput = input;
            // Simulate the behavior of the real DeleteInputBoundary implementation
        }

        public String getExecutedInput() {
            return executedInput;
        }
    }

    @Test
    void testExecute() {
        // Create a stub implementation of DeleteInputBoundary
        DeleteInputBoundaryStub deleteInputBoundaryStub = new DeleteInputBoundaryStub();

        // Create DeleteController with the stub implementation
        DeleteController deleteController = new DeleteController(deleteInputBoundaryStub);

        // Test execute method
        String testInput = "testInput";
        deleteController.execute(testInput);

        // Verify the behavior by checking the executedInput in the stub
        String executedInput = deleteInputBoundaryStub.getExecutedInput();
        assertEquals(testInput, executedInput);
    }
}