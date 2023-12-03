package use_case.logout;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class LogoutInteractor implements LogoutInputBoundary {
    final LogoutOutputBoundary userPresenter;
    final User logged_in;

    public LogoutInteractor(LogoutOutputBoundary userPresenter, User user) {
        this.logged_in = user;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute() {

        LogoutOutputData logoutOutputData = new LogoutOutputData(true);
        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("", "", LocalDateTime.parse("2023-12-01T14:58:50.150"), 0, "White");
        this.logged_in.copyUser(user);
        userPresenter.prepareSuccessView(logoutOutputData);
    }
}