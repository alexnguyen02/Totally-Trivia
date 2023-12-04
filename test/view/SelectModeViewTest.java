package view;

import data_access.InMemorySelectModeAccessObject;
import entity.CommonQuestionStorage;
import entity.Question;
import entity.QuestionStorage;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.question.QuestionViewModel;
import interface_adaptors.select_mode.SelectModeController;
import interface_adaptors.select_mode.SelectModePresenter;
import interface_adaptors.select_mode.SelectModeViewModel;
import org.junit.jupiter.api.Test;
import use_case.select_mode.SelectModeDataObjectInterface;
import use_case.select_mode.SelectModeInputBoundary;
import use_case.select_mode.SelectModeInteractor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectModeViewTest {

    @Test
    public void selectModeViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        QuestionViewModel questionViewModel = new QuestionViewModel();

        SelectModeViewModel selectModeViewModel = new SelectModeViewModel();
        SelectModeDataObjectInterface selectModeDataObject = new InMemorySelectModeAccessObject();
        SelectModePresenter selectModePresenter =
                new SelectModePresenter(viewManagerModel, selectModeViewModel, questionViewModel);
        QuestionStorage questionStorage = new CommonQuestionStorage();

        SelectModeInputBoundary selectModeInputInteractor =
                new SelectModeInteractor(selectModeDataObject, selectModePresenter, questionStorage);
        SelectModeController selectModeController = new SelectModeController(selectModeInputInteractor);
        SelectModeView selectModeView = new SelectModeView(selectModeViewModel, selectModeController);

        assertEquals("select mode", selectModeView.viewName);
        assertEquals("Customize Your Trivia", selectModeView.getTitle());
        assertEquals("Category", selectModeView.getCategoryLabel());
        assertEquals("Difficulty level", selectModeView.getDifficultyLevelLabel());
        assertEquals("Number of questions", selectModeView.getNumQuestionsLabel());
    }
}
