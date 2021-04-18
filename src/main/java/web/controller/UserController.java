package web.controller;

import org.springframework.web.bind.annotation.*;
import web.Dao.UserDao;
import web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller

public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @GetMapping("/")
    public String showAllUser(Model model) {
        List<User> allUs = userDao.getAllUsers();
        model.addAttribute("allUs",allUs);
        return "allUsers";
    }

    @GetMapping("/addNewUser")
    public String addNewUser (Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "addNewUser";
    }
    @PostMapping()
    public String saveUser (@ModelAttribute("user") User user) {
        userDao.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/userUpdate/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userDao.getUserId(id));
        return "userUpdate";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userDao.updateInfo(id,user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userDao.deleteUser(id);
        return "redirect:/";
    }
}
