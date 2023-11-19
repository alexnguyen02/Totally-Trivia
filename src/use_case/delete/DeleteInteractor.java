package src.use_case.delete;

public class DeleteInteractor implements DeleteInputBoundary {
    final DeleteUserDataAccessInterface userDataAccessObject;
    final DeleteOutputBoundary userPresenter;

    public DeleteInteractor(DeleteUserDataAccessInterface deleteUserDataAccessInterface,
                           DeleteOutputBoundary deleteOutputBoundary){
        this.userDataAccessObject = deleteUserDataAccessInterface;
        this.userPresenter = deleteOutputBoundary;
    }
    @Override
    public void execute(){
        String username = userDataAccessObject.delete();
        DeleteOutputData DeleteOutputData = new DeleteOutputData(username);
        userPresenter.prepareSuccessView(DeleteOutputData);
    }

}
