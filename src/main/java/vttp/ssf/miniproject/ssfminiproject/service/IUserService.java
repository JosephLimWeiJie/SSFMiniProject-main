package vttp.ssf.miniproject.ssfminiproject.service;

import vttp.ssf.miniproject.ssfminiproject.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    boolean login(String username);

    boolean logout(String username);

    boolean create(String username);

    Map<Integer, User> getAllUsers();
}
