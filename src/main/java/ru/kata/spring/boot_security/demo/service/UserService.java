package ru.kata.spring.boot_security.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> readUsers();
    User getUser(long id);
    void saveUser(User user, String[] selectedRoles);
    void updateUser(User user, String[] selectedRoles);
    void deleteUser (long user);
}
