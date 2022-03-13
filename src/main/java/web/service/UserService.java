package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User findUserByID(long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(long id);

}
