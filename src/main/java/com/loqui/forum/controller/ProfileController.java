package com.loqui.forum.controller;

import com.loqui.forum.entity.User;
import com.loqui.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {
    UserService userService;
    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
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
