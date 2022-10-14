package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleCrudRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleCrudRepository roleRepository;

    public RoleServiceImpl() {
    }

    @Autowired
    public RoleServiceImpl(RoleCrudRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRole(String name) {
        return roleRepository.findByRoleName(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> convertNamesToRoles(String[] names) {
        List<Role> userRoles = new ArrayList<>();
        for (String s : names) userRoles.add(findRole(s));
        return userRoles;
    }
    @Override
    public String[] convertRolesToNames(List<Role> roles){
        String[] names = new String[roles.size()];
        for (int i = 0; i<roles.size(); i++){
            names[i] = roles.get(i).getRoleName();
        }
        return names;
    }


}
