package src.interface_adaptors.delete;

import src.use_case.delete.DeleteOutputBoundary;
import src.use_case.delete.DeleteOutputData;

import java.util.StringJoiner;


public class DeletePresenter implements DeleteOutputBoundary {
    private final DeleteViewModel deleteViewModel;

    public DeletePresenter(DeleteViewModel deleteViewModel) {
        this.deleteViewModel = deleteViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteOutputData response) {
        DeleteState deleteState = deleteViewModel.getState();
        String name = response.getUsername();


        deleteState.setUsername(name);
        this.deleteViewModel.setState(deleteState);
        deleteViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
