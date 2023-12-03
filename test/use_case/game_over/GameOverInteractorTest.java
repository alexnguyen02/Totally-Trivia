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
        userRepository.save(logged_in_user);

        GameOverOutputBoundary successPresenter = new GameOverOutputBoundary() {
            @Override
            public void prepareSuccessView(GameOverOutputData view_name) {
                // 2 things to check: the output data is correct, and the user's points have been updated in the DAO.
                assertEquals("select_mode", view_name.getViewName());
                Integer user_points = userRepository.get(logged_in_user.getName()).getPoints();
                assert user_points.equals(57);
            }

            //@Override
            //public void prepareFailView(String error) {
                //fail("Use case failure is unexpected.");
            //}
        };

        GameOverInputBoundary interactor = new GameOverInteractor(successPresenter, userRepository, logged_in_user);
        interactor.execute(inputData);
    }

}