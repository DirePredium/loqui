package com.loqui.forum.controller;

import com.loqui.forum.entity.Enum.Role;
import com.loqui.forum.entity.User;
import com.loqui.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") User user){
        user.setActive(true);
        user.setRoles(Set.of(Role.USER));
        userService.save(user);
        return "redirect:/";
    }
}
