package com.loqui.forum.controller;

import com.loqui.forum.entity.Post;
import com.loqui.forum.entity.User;
import com.loqui.forum.repository.PostRepository;
import com.loqui.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public String index(Model model){
        List<Post> list = postService.findAll();
        model.addAttribute("posts", list);
        return "posts/post";
    }

    @GetMapping("create")
    public String indexCreate(Model model){
        model.addAttribute("post", new Post());
        return "posts/post_create";
    }

    @PostMapping()
    public String save(@AuthenticationPrincipal User user, @ModelAttribute("post") Post post){
        post.setDateCreate();
        post.setUser(user);
        postService.save(post);
        return "redirect:/posts";
    }

}
