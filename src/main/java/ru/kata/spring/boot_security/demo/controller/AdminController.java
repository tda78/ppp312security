package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUsers(Model model) {
        model.addAttribute("users", userService.readUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user, @RequestParam(value = "selectRoles") String[] selectedRoles) {
        userService.saveUser(user, selectedRoles);
        return ("redirect:/admin");
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") long id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("selectedRoles", roleService.convertRolesToNames(user.getRoles()));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id, @RequestParam(value = "selectRoles") String[] selectedRoles) {
        userService.updateUser(user, selectedRoles);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "delete";
    }

    @DeleteMapping("/{id}")
    public String confirmDeleteUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
