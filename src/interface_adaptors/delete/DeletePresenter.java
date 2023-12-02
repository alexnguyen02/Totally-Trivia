package interface_adaptors.delete;

import use_case.delete.DeleteOutputBoundary;
import use_case.delete.DeleteOutputData;


public class DeletePresenter implements DeleteOutputBoundary {
    private final DeleteViewModel deleteViewModel;

    public DeletePresenter(DeleteViewModel deleteViewModel) {
        this.deleteViewModel = deleteViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteOutputData response) {
        DeleteState deleteState = deleteViewModel.getState();




        deleteState.setUsernames(response.getUsernames());
        deleteViewModel.setState(deleteState);
        deleteViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
