package use_case.delete;


import entity.User;

public interface DeleteUserDataAccessInterface {

    void save(User user);
    String delete(String s);
}
