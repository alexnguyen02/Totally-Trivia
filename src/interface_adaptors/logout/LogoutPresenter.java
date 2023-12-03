package interface_adaptors.logout;


import interface_adaptors.ViewManagerModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;


public class LogoutPresenter implements LogoutOutputBoundary {
    private final LogoutViewModel logoutViewModel;
    private final ViewManagerModel viewManagerModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel, LogoutViewModel logoutViewModel) {

        this.logoutViewModel = logoutViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {

        logoutViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("welcome");
        viewManagerModel.firePropertyChanged();
    }


}
