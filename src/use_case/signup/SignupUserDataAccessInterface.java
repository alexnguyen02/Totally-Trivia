package src.use_case.signup;

import src.entity.User;

import src.entity.User;

public interface SignupUserDataAccessInterface {
    User get(String username);

    boolean existsByName(String identifier);

    void save(User user);
}
