package view;

import app.DeleteUseCaseFactory;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeleteViewModel;
import org.junit.jupiter.api.Test;
import use_case.delete.DeleteUserDataAccessInterface;
import view.DeleteView;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteViewTest {

    @Test
    public void testViewName() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DeleteViewModel deleteViewModel = new DeleteViewModel();
        UserFactory userFactory = new CommonUserFactory();
        DeleteUserDataAccessInterface userDataAccessObject = new InMemoryUserDataAccessObject();
        User user = userFactory.create("Furiosa", "RememberMe", LocalDateTime.now(), 50, "Red");
        User for_repository = userFactory.create("","", LocalDateTime.now(), 0, "White");
        for_repository.copyUser(user);
        userDataAccessObject.save(for_repository);
        String s = user.getName();
        DeleteController deleteController = DeleteUseCaseFactory.createUserDeleteUseCase(viewManagerModel, deleteViewModel, user, userDataAccessObject);

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

//    @Test
//    public void testActionPerformed() {
//        DeleteController deleteController = new DeleteController(null);
//        DeleteViewModel deleteViewModel = new DeleteViewModel();
//        DeleteView deleteView = new DeleteView(deleteController, deleteViewModel);
//
//        // Assuming the actionPerformed method should show a confirmation dialog
//        deleteView.actionPerformed(null);
//        // Add assertions based on the expected behavior of the actionPerformed method
//    }

//    @Test
//    public void testPropertyChange() {
//        DeleteController deleteController = new DeleteController(null);
//        DeleteViewModel deleteViewModel = new DeleteViewModel();
//        DeleteView deleteView = new DeleteView(deleteController, deleteViewModel);
//
//        // Assuming the propertyChange method should show a message dialog
//        deleteView.propertyChange(null);
//        // Add assertions based on the expected behavior of the propertyChange method
//    }

    // Add more test methods as needed to cover other functionality in DeleteView
}