package web.controller;

import org.springframework.web.bind.annotation.*;
import web.dao.RoleDao;
import web.model.Role;
import web.service.UserService;
import web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    private final  RoleDao roleDao;


    public UserController(UserService userService, RoleDao roleDao) {
        this.userService = userService;

        this.roleDao = roleDao;
    }

    @GetMapping("/pageForUser")
    public String userPage() {
        return "pageForUser";
    }

    @GetMapping("/admin")
    public String showAllUser(Model model) {
        List<User> allUs = userService.getAllUsers();
        model.addAttribute("allUs",allUs);
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser (Model model) {
        model.addAttribute("roles", roleDao.getAllRoles());
        model.addAttribute("user", new User());
        return "addNewUser";
    }
    @PostMapping("/saveUser")
    public String saveUser (@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/userUpdate/{id}")
    public String edit(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("user", userService.getUserId(id));
        model.addAttribute("roles", roleDao.getAllRoles());

        return "userUpdate";
    }

    @PostMapping("/userUpdate")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles", required = false)String [] roleList) {
        userService.updateUserAndRoles(user, roleList);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
