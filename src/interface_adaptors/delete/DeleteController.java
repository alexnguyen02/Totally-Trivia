package interface_adaptors.delete;

import use_case.delete.DeleteInputBoundary;


public class DeleteController {

    final DeleteInputBoundary deleteUseCaseInteractor;
    public DeleteController(DeleteInputBoundary deleteUseCaseInteractor) {
        this.deleteUseCaseInteractor = deleteUseCaseInteractor;
    }


    public void execute() {


        deleteUseCaseInteractor.execute();
    }
}

