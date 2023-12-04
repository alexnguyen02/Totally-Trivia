package view;

import app.DeleteUseCaseFactory;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeleteState;
import interface_adaptors.delete.DeleteViewModel;
import org.junit.jupiter.api.Test;
import use_case.delete.DeleteUserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import java.beans.PropertyChangeEvent;


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