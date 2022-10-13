package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    UserService service;

    public UserService getService() {
        return service;
    }

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String printUsers(Model model) {
        model.addAttribute("users", service.readUsers());
        return "users";
    }

 /*   @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", service.getUser(id));
        model.addAttribute("user", new User());

        return "userInfo";
    }
*/
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", service.getAllRoles());
        return "new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user,
             @RequestParam(value = "selectRoles") String[] selectedRoles) {
        service.saveUser(user,selectedRoles);
        return ("redirect:/admin");
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") long id) {
        User user = service.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", service.getAllRoles());
        model.addAttribute("selectedRoles", service.convertRolesToNames(user.getRoles()));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute ("user") User user,
                         @PathVariable("id") long id,
                         @RequestParam(value = "selectRoles") String[] selectedRoles){
        service.updateUser(user, selectedRoles);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", service.getUser(id));
        model.addAttribute("allRoles", service.getAllRoles());

        return "delete";
    }

    @DeleteMapping("/{id}")
    public String confirmDeleteUser(@ModelAttribute ("user") User user,@PathVariable("id") long id){
        service.deleteUser(id);
        return "redirect:/admin";
    }
}
