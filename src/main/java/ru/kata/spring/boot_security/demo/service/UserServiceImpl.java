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
    private RoleCrudRepository roleRepository;

    public UserCrudRepository getUserRepository() {
        return userRepository;
    }
@Autowired
    public UserServiceImpl(UserCrudRepository dao, RoleCrudRepository roleDao) {
        this.userRepository = dao;
        this.roleRepository = roleDao;
    }
    @Transactional
    @Override
    public List<User> readUsers() {
        List<User> users =(List<User>) userRepository.findAll();
        return users;
    }

    @Override
    public User getUser(long id) {

        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public void saveUser(User user, String[] selectedRoles) {
        user.setRoles(convertNamesToRoles(selectedRoles));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user, String[] selectedRoles) {
        user.setRoles(convertNamesToRoles(selectedRoles));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Role findRole(String name) {
        return roleRepository.getById(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public String[] convertRolesToNames(List<Role> roles){
        String[] names = new String[roles.size()];
        for (int i = 0; i<roles.size(); i++){
            names[i] = roles.get(i).getRoleName();
        }
        return names;
    }

    private List<Role> convertNamesToRoles(String[] names){
        List<Role>userRoles = new ArrayList<>();
        for (String s:names) userRoles.add(findRole(s));
        return userRoles;
    }

    public UserServiceImpl() {
    }
@Transactional
    // @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        return user;
    }
}
