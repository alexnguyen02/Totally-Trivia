package interface_adaptors.question;

public class CreateGameOverController {

    final CreateGameOverPresenter createGameOverPresenter;
    public CreateGameOverController(CreateGameOverPresenter createGameOverPresenter) {
        this.createGameOverPresenter = createGameOverPresenter;
    }

    public void execute(QuestionState inputState) {
        createGameOverPresenter.prepareSuccessView(inputState);
    }
}
