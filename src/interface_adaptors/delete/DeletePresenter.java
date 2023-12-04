package interface_adaptors.delete;

import interface_adaptors.ViewManagerModel;
import use_case.delete.DeleteOutputBoundary;
import use_case.delete.DeleteOutputData;


public class DeletePresenter implements DeleteOutputBoundary {
    private final DeleteViewModel deleteViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeletePresenter(ViewManagerModel viewManagerModel, DeleteViewModel deleteViewModel) {
        this.deleteViewModel = deleteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeleteOutputData response) {
        DeleteState deleteState = deleteViewModel.getState();

        deleteState.setUsernames(response.getUsernames());
        deleteViewModel.setState(deleteState);
        deleteViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("welcome");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
