package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleCrudRepository extends JpaRepository<Role, String> {
}
