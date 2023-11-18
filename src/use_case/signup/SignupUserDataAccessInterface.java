package use_case.signup;

import entity.User;

import src.entity.User;

public interface SignupUserDataAccessInterface {
    void save(User user);

    User get(String username);

    boolean existsByName(String identifier);

    void save(User user);
}
