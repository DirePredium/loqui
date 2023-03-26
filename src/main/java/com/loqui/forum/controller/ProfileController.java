package com.loqui.forum.controller;

import com.loqui.forum.entity.User;
import com.loqui.forum.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {
    UserServise userServise;
    @Autowired
    public ProfileController(UserServise userServise) {
        this.userServise = userServise;
    }
    @GetMapping("/{id}")
    private String index(@PathVariable("id") Long id, Model model){
        User user = userServise.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping
    private String index(Model model){
        return "profile";
    }

    @ModelAttribute("id_current_user")
    public Long currentUserId(){
        return 1L;
    }
}
