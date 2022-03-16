package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.model.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}
