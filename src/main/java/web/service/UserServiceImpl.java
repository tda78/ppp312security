package web.service;

import web.dao.UserRepository;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository dao;

    @Autowired
    public UserServiceImpl(UserRepository dao) {
        this.dao = dao;
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
}
