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
    public void execute(){
        String id = new String();
        String p = new String();
        String username = userDataAccessObject.delete(id, p);
        DeleteOutputData DeleteOutputData = new DeleteOutputData(username);
        userPresenter.prepareSuccessView(DeleteOutputData);
    }

}
