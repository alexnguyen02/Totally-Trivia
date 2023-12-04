package view;

import app.SelectModeUseCaseFactory;
import data_access.InMemorySelectModeAccessObject;
import entity.CommonQuestionStorage;
import entity.QuestionStorage;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.question.QuestionViewModel;
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
        SelectModeDataObjectInterface selectModeDataAccessObject = new InMemorySelectModeAccessObject();
        SelectModePresenter selectModePresenter =
                new SelectModePresenter(viewManagerModel, selectModeViewModel, questionViewModel);
        QuestionStorage questionStorage = new CommonQuestionStorage();

        SelectModeInputBoundary selectModeInputInteractor =
                new SelectModeInteractor(selectModeDataAccessObject, selectModePresenter, questionStorage);
        SelectModeView selectModeView = SelectModeUseCaseFactory.create(viewManagerModel, selectModeViewModel,
                selectModeDataAccessObject, questionStorage, questionViewModel);

        assert selectModeView != null;
        assertEquals("select mode", selectModeView.viewName);
        assertEquals("Customize Your Trivia", selectModeView.getTitle());
        assertEquals("Category", selectModeView.getCategoryLabel());
        assertEquals("Difficulty level", selectModeView.getDifficultyLevelLabel());
        assertEquals("Number of questions", selectModeView.getNumQuestionsLabel());
    }
}
