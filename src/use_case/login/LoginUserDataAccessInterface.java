package src.use_case.login;

import src.entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}

