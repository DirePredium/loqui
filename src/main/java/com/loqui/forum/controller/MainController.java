package com.loqui.forum.controller;

import com.loqui.forum.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String main(Model model){
        User user = new User();
        user.setUsername("User");
        model.addAttribute("current_user", user);
        return "index";
    }

}
