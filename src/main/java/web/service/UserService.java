package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUsers();
    User findUserByID(long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(long id);

    Role getRoleByName(String name);
}
