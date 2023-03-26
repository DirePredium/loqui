package com.loqui.forum.controller;

import com.loqui.forum.entity.User;
import com.loqui.forum.repository.UserRepository;
import com.loqui.forum.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.function.Supplier;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    UserServise userServise;
    @Autowired
    public UserController(UserServise userServise) {
        this.userServise = userServise;
    }

    @PostMapping
    private void save(){

    }
}
