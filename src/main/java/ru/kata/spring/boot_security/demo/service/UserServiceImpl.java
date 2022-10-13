package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
 //import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleCrudRepository;
import ru.kata.spring.boot_security.demo.dao.UserCrudRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserCrudRepository dao;
    private RoleCrudRepository roleDao;

    public UserCrudRepository getDao() {
        return dao;
    }
@Autowired
    public UserServiceImpl(UserCrudRepository dao, RoleCrudRepository roleDao) {
        this.dao = dao;
        this.roleDao = roleDao;
    }
    @Transactional
    @Override
    public List<User> readUsers() {
        List<User> users =(List<User>)dao.findAll();
 //       for (User user:users) {
 //           if (user.getRoles()==null) {user.addRole(roleDao.getById("USER"));}
 //           user.setUser(user.getRoles().contains(roleDao.getById("USER")));
 //           user.setAdmin(user.getRoles().contains(roleDao.getById("ADMIN")));
  //      }
        return users;
    }

    @Override
    public User getUser(long id) {

        User user = dao.findById(id).get();
        return user;
    }

    @Override
    public void saveUser(User user, String[] selectedRoles) {
        user.setRoles(convertNamesToRoles(selectedRoles));
        //applyRoles(user);
        dao.save(user);
    }

    @Override
    public void updateUser(User user, String[] selectedRoles) {
        user.setRoles(convertNamesToRoles(selectedRoles));
        dao.save(user);
    }

    @Override
    public void deleteUser(long id) {
        dao.deleteById(id);
    }

    @Override
    public Role findRole(String name) {
        return roleDao.getById(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
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
        User user = dao.findUserByUsername(username);

    /*    return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(username)
                .password(user.getPassword())
                .roles(convertRolesToNames(user.getRoles()))
                .build();
*/
        return user;
    }
}
