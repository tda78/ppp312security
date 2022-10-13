package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserCrudRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String name);
}
