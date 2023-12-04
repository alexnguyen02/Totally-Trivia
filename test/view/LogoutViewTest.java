package view;

import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeleteViewModel;
import interface_adaptors.logout.LogoutController;
import interface_adaptors.logout.LogoutViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutViewTest {
    @Test
    public void testViewName() {
        LogoutController deleteController = new LogoutController(null);
        LogoutViewModel deleteViewModel = new LogoutViewModel();
        LogoutView deleteView = new LogoutView(deleteController, deleteViewModel);

        assertEquals("logout", deleteView.viewName);
    }

    @Test
    public void testButtonsExist() {
        LogoutController deleteController = new LogoutController(null);
        LogoutViewModel deleteViewModel = new LogoutViewModel();
        LogoutView deleteView = new LogoutView(deleteController, deleteViewModel);

        assertNotNull(deleteView.logout);
    }

}