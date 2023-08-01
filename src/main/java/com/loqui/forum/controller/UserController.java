package com.loqui.forum.controller;

import com.loqui.forum.entity.User;
import com.loqui.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String showUserById(Model model, @PathVariable("id") Long id){
        Optional<User> optionalUser = userService.findById(id);

        if(optionalUser.isEmpty()) {
            model.addAttribute("errorMessage", "Can`t define user");
            return "error";
        }
        model.addAttribute("user", optionalUser.get());
        return "users/user";
    }

    @GetMapping
    public String index(Model model, @RequestParam(required = false, name = "username") String username){
        List<User> users = null;
        if(username != null && !username.isEmpty()){
            users = userService.findByUsernameContaining(username);
        }
        model.addAttribute("users_find", users);
        return "users/find_user";
    }

}
