package view;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.logout.LogoutController;
import interface_adaptors.logout.LogoutViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutViewTest {
    @Test
    public void testViewName() {
        LogoutController deleteController = new LogoutController(null);
        LogoutViewModel deleteViewModel = new LogoutViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LogoutView deleteView = new LogoutView(deleteController, deleteViewModel, viewManagerModel);

        assertEquals("logout", deleteView.viewName);
    }

    @Test
    public void testButtonsExist() {
        LogoutController logOutController = new LogoutController(null);
        LogoutViewModel logOutViewModel = new LogoutViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LogoutView logOutView = new LogoutView(logOutController, logOutViewModel, viewManagerModel);

        assertNotNull(logOutView.logout);
    }

}