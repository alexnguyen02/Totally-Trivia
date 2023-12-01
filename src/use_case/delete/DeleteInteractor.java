package use_case.delete;

public class DeleteInteractor implements DeleteInputBoundary {
    final DeleteUserDataAccessInterface userDataAccessObject;
    final DeleteOutputBoundary userPresenter;

    public DeleteInteractor(DeleteUserDataAccessInterface deleteUserDataAccessInterface,
                           DeleteOutputBoundary deleteOutputBoundary){
        this.userDataAccessObject = deleteUserDataAccessInterface;
        this.userPresenter = deleteOutputBoundary;
    }
    @Override
    public void execute(String s){

        String username = userDataAccessObject.delete(s);
        DeleteOutputData deleteOutputData = new DeleteOutputData(username);
        userPresenter.prepareSuccessView(deleteOutputData);
    }

}
