package use_case.signup;

import entity.User;

public interface SignupUserDataAccessInterface {
    User get(String username);

    boolean existsByName(String identifier);

    void save(User user);
}
