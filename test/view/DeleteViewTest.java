package view;

import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeleteViewModel;
import org.junit.jupiter.api.Test;
import view.DeleteView;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteViewTest {

    @Test
    public void testViewName() {
        DeleteController deleteController = new DeleteController(null);
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        DeleteView deleteView = new DeleteView(deleteController, deleteViewModel);

        assertEquals("delete", deleteView.viewName);
    }

    @Test
    public void testButtonsExist() {
        DeleteController deleteController = new DeleteController(null);
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        DeleteView deleteView = new DeleteView(deleteController, deleteViewModel);

        assertNotNull(deleteView.delete);
    }

    @Test
    public void testActionPerformed() {
        DeleteController deleteController = new DeleteController(null);
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        DeleteView deleteView = new DeleteView(deleteController, deleteViewModel);

        // Assuming the actionPerformed method should show a confirmation dialog
        deleteView.actionPerformed(null);
        // Add assertions based on the expected behavior of the actionPerformed method
    }


}