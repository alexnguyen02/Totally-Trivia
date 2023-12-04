package view;

import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeleteState;
import interface_adaptors.delete.DeleteViewModel;
import org.junit.jupiter.api.Test;
import view.DeleteView;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

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
    void testPropertyChange() {
        // Create a DeleteView instance
        DeleteController deleteController = new DeleteController(null);
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        DeleteView deleteView = new DeleteView(deleteController, deleteViewModel);

        // Create a DeleteState for testing
        DeleteState testState = new DeleteState();
        testState.setUsernames("testUsernames");

        // Trigger a PropertyChangeEvent
        PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(
                this, "propertyName", null, testState
        );
        try {
            deleteView.propertyChange(propertyChangeEvent);
        } catch (Exception e) {
            assert false;
        }
        finally {
            assert true;
        }
    }

    @Test
    void testActionPerformed() {
        // Create a DeleteView instance
        DeleteController deleteController = new DeleteController(null);
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        DeleteView deleteView = new DeleteView(deleteController, deleteViewModel);

        // Create a DeleteState for testing
        try {
            deleteView.actionPerformed(null);
        } catch (Exception e) {
            assert false;
        }
        finally {
            assert true;
        }
    }
        // Call propertyChange method with the test event









    }