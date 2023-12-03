package use_case.delete;

import entity.User;

public class DeleteInteractor implements DeleteInputBoundary {
    final DeleteUserDataAccessInterface userDataAccessObject;
    final DeleteOutputBoundary userPresenter;

    final User user;

    public DeleteInteractor(DeleteUserDataAccessInterface deleteUserDataAccessInterface,
                           User user, DeleteOutputBoundary deleteOutputBoundary){
        this.userDataAccessObject = deleteUserDataAccessInterface;
        this.userPresenter = deleteOutputBoundary;
        this.user = user;
    }
    @Override
    public void execute(String s){
        String u = user.getName();
        String username = userDataAccessObject.delete(u);
        DeleteOutputData deleteOutputData = new DeleteOutputData(username);
        userPresenter.prepareSuccessView(deleteOutputData);
    }

}
