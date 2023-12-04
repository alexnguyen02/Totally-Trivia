package view;

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
        LogoutController logOutController = new LogoutController(null);
        LogoutViewModel logOutViewModel = new LogoutViewModel();
        LogoutView logOutView = new LogoutView(logOutController, logOutViewModel);

        assertNotNull(logOutView.logout);
    }

}