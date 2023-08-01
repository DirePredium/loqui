package com.loqui.forum.controller;

import com.loqui.forum.entity.Enum.Role;
import com.loqui.forum.entity.User;
import com.loqui.forum.service.PostService;
import com.loqui.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    private String index(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("new_user", new User());
        model.addAttribute("roles", new ArrayList<Role>(Arrays.asList(Role.values())) );
        return "admin/user";
    }

    @GetMapping("/users/{id}")
    public String update(@PathVariable Long id, Model model){
        Optional<User> optionalUser = userService.findById(id);

        if(optionalUser.isEmpty()) {
            model.addAttribute("errorMessage", "Can`t define user");
            return "error";
        }
        model.addAttribute("user", optionalUser.get());
        model.addAttribute("roles", new ArrayList<Role>(Arrays.asList(Role.values())) );
        return "/admin/user_update";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        System.out.println("Delete?");
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @ModelAttribute("id_current_user")
    public Long currentUserId(){
        return 1L;
    }
}
