package web.controller;

import web.model.Role;
import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UsersController {

    private final UserService service;

    @Autowired
    public UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String printUsers(Model model){
        List<User> users = service.getUsers();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/{id}")
    public String updateUser(@PathVariable("id") int id, Model model){
        User user = service.findUserByID(id);
        model.addAttribute("user", user);
        return "user";
    }
    @GetMapping("/new")
    public String newUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "new";
    }

    @PatchMapping()
    public String updateeUser(HttpServletRequest request, @ModelAttribute("user") User user){
        user.setRoles(new HashSet<Role>());
        if(request.getParameter("isUser") != null){
            user.getRoles().add(service.getRoleByName("USER"));
        }
        if(request.getParameter("isAdmin") != null){
            user.getRoles().add(service.getRoleByName("ADMIN"));
        }

        service.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/new")
    public String saveUser(HttpServletRequest request, @ModelAttribute("user") User user){
        user.setRoles(new HashSet<Role>());
        if(request.getParameter("isUser")!=null){
            user.getRoles().add(service.getRoleByName("USER"));
        }
        if(request.getParameter("isAdmin")!=null){
            user.getRoles().add(service.getRoleByName("ADMIN"));
        }
        service.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/del/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model){
        User user = service.findUserByID(id);
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @DeleteMapping("/del")
    public String delUser(@ModelAttribute("user") User user){
        service.deleteUser((int) user.getId());
        return "redirect:/admin";
    }
}
