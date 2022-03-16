package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.dao.RoleDao;
import web.dao.UserRepository;
import web.model.Role;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository dao;
    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserRepository dao) {
        this.dao = dao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>)(dao.findAll());
    }

    @Override
    public User findUserByID(long id) {
        return dao.findById(id).get();
    }

    @Override
    public void saveUser(User user) {
        dao.save(user);
    }

    @Override
    public void updateUser(User user) {
        dao.save(user);
    }

    @Override
    public void deleteUser(long id) {
        dao.delete(dao.findById(id).get());
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findByName(username);
    }
}
