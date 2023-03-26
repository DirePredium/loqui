package com.loqui.forum.controller;

import com.loqui.forum.entity.Enum.Role;
import com.loqui.forum.entity.User;
import com.loqui.forum.repository.UserRepository;
import com.loqui.forum.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    UserServise userServise;
    @Autowired
    public AdminController(UserServise userServise) {
        this.userServise = userServise;
    }

    @GetMapping("/users")
    private String index(Model model){
        model.addAttribute("users", userServise.findAll());
        model.addAttribute("user", new User());
        model.addAttribute("roles", new ArrayList<Role>(Arrays.asList(Role.values())) );
        return "admin/user";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {
        userServise.save(user);
        return "redirect:/admin/users";
    }

    @ModelAttribute("id_current_user")
    public Long currentUserId(){
        return 1L;
    }
}
