package app;

import entity.User;
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
    public static DeleteController createUserDeleteUseCase(DeleteViewModel deleteViewModel, User user, DeleteUserDataAccessInterface deleteUserDataAccessObject) {
        // Notice how we pass this method's parameters to the Presenter.
        DeleteOutputBoundary deleteOutputBoundary = new DeletePresenter(deleteViewModel);


        DeleteInputBoundary deleteInteractor = new DeleteInteractor(deleteUserDataAccessObject, user,deleteOutputBoundary);

        return new DeleteController(deleteInteractor);

    }
}
