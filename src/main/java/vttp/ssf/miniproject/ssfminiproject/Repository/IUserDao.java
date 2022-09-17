package vttp.ssf.miniproject.ssfminiproject.Repository;

import vttp.ssf.miniproject.ssfminiproject.model.User;

import java.util.Map;

public interface IUserDao {

    boolean addUserToDb(User user);

    Integer getNewUserId();

    Map<Integer, User> getAllUsers();

    User getUserInDb(String username);

    boolean login(User user);

    boolean logout(User user);
}
