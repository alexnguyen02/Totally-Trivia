package use_case.game_over;

import entity.User;

public interface GameOverUserDataAccessInterface {

    void save(User user);

    void changePoints(String userId, Integer points);

}
