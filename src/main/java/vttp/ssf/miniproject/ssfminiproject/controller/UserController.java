package vttp.ssf.miniproject.ssfminiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vttp.ssf.miniproject.ssfminiproject.model.User;
import vttp.ssf.miniproject.ssfminiproject.service.UserServiceImpl;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
        String username = user.getUsername();
        boolean isLoggedIn = userServiceImpl.login(username);
        model.addAttribute(user);
        if (!isLoggedIn) {
            redirectAttributes.addFlashAttribute("error", "Invalid username.");
        }
        return "redirect:";
    }

    @PostMapping("/logout")
    public String submitLogoutForm(@ModelAttribute("user") User user, Model model) {
        String username = user.getUsername();
        boolean isUserInDb = userServiceImpl.logout(username);
        return "redirect:login";
    }

    @GetMapping("/users")
    public ResponseEntity<Map<Integer, User>> getAllUsers() {
        Map<Integer, User> users = userServiceImpl.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String submitRegisterForm(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        String username = user.getUsername();
        boolean isCreated = userServiceImpl.create(username);
        if (isCreated) {
            redirectAttributes.addFlashAttribute("success", "Your account is created sucessfully. You may log in now.");
        } else {
            redirectAttributes.addFlashAttribute("error", "This username has been taken.");
        }
        return "redirect:";
    }

}
