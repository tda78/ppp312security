package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleCrudRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String name);
}
