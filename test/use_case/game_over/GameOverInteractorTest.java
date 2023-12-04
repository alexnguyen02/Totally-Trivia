package use_case.game_over;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class GameOverInteractorTest {
    @Test
    public void successTest() {
        GameOverInputData inputData = new GameOverInputData("select_mode", 12);
        GameOverUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();
        User logged_in_user = userFactory.create("Furiosa", "RememberMe", LocalDateTime.now(), 45, "Red");
        //two users needed to avoid points being added on twice, as InMemoryUserDataAccessObject stores the user as a user, not a line of text.
        User for_repository = userFactory.create("","", LocalDateTime.now(), 0, "White");
        for_repository.copyUser(logged_in_user);
        userRepository.save(for_repository);

        GameOverOutputBoundary successPresenter = new GameOverOutputBoundary() {
            @Override
            public void prepareSuccessView(GameOverOutputData gameOverOutputData) {
                assertEquals("select_mode", gameOverOutputData.getViewName());
                Integer user_points = userRepository.get(logged_in_user.getName()).getPoints();
                assertEquals(new Integer(57), user_points);
            };
        };

        GameOverInputBoundary interactor = new GameOverInteractor(successPresenter, userRepository, logged_in_user);
        interactor.execute(inputData);
    }

}