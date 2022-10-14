package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.repository.RoleCrudRepository;
import ru.kata.spring.boot_security.demo.repository.UserCrudRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserCrudRepository userRepository;
    private RoleService roleService;

    public UserCrudRepository getUserRepository() {
        return userRepository;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public UserServiceImpl(UserCrudRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Autowired

    @Transactional
    @Override
    public List<User> readUsers() {
        List<User> users =(List<User>) userRepository.findAll();
        return users;
    }

    @Override
    public User getUser(long id) {
        return (userRepository.findById(id).get());
    }

    @Override
    public void saveUser(User user, String[] selectedRoles) {
        user.setRoles(roleService.convertNamesToRoles(selectedRoles));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user, String[] selectedRoles) {
        user.setRoles(roleService.convertNamesToRoles(selectedRoles));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }





    public UserServiceImpl() {
    }
@Transactional
    // @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (userRepository.findUserByMail(username));

    }
}
