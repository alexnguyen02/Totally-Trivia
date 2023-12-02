package use_case.logout;

import use_case.delete.DeleteOutputData;

public class LogoutInteractor implements LogoutInputBoundary {
    final LogoutUserDataAccessInterface userDataAccessObject;
    final LogoutOutputBoundary userPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessObject, LogoutOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(String s) {

        LogoutOutputData logoutOutputData = new LogoutOutputData(true, s);
        userPresenter.prepareSuccessView(logoutOutputData);
    }
}