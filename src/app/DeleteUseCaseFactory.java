package app;

import entity.User;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.delete.DeleteController;
import interface_adaptors.delete.DeletePresenter;
import interface_adaptors.delete.DeleteViewModel;
import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
import use_case.delete.DeleteUserDataAccessInterface;

public class DeleteUseCaseFactory {

    public DeleteUseCaseFactory() {
    }
    public static DeleteController createUserDeleteUseCase(ViewManagerModel viewManagerModel, DeleteViewModel deleteViewModel, User user, DeleteUserDataAccessInterface deleteUserDataAccessObject) {
        DeleteOutputBoundary deleteOutputBoundary = new DeletePresenter(viewManagerModel, deleteViewModel);


        DeleteInputBoundary deleteInteractor = new DeleteInteractor(deleteUserDataAccessObject, user,deleteOutputBoundary);

        return new DeleteController(deleteInteractor);

    }
}
