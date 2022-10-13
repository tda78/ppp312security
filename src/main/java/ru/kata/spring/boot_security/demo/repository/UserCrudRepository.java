package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserCrudRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String name);
}
