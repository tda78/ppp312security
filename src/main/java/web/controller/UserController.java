package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping("/user")
    public String userPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User auth = (User) service.loadUserByUsername(name);
        model.addAttribute("user", auth);
        return "userInfo";
    }
}