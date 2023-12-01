package interface_adaptors.delete;

import use_case.delete.DeleteInputBoundary;

public class DeleteController {
    final DeleteInputBoundary deleteAllUseCaseInteractor;

    public DeleteController(DeleteInputBoundary deleteAllUseCaseInteractor) {
        this.deleteAllUseCaseInteractor = deleteAllUseCaseInteractor;
    }

    public void execute(String s) {


        deleteAllUseCaseInteractor.execute(s);
    }
}