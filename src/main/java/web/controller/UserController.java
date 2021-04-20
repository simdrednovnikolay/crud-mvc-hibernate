package web.controller;

import org.springframework.web.bind.annotation.*;
import web.Service.UserService;
import web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUser(Model model) {
        List<User> allUs = userService.getAllUsers();
        model.addAttribute("allUs",allUs);
        return "allUsers";
    }

    @GetMapping("/addNewUser")
    public String addNewUser (Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "addNewUser";
    }
    @PostMapping("/saveUser")
    public String saveUser (@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/userUpdate/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserId(id);
        model.addAttribute("user", user);
        return "userUpdate";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id,user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
