package com.loqui.forum.controller;

import com.loqui.forum.entity.Image;
import com.loqui.forum.entity.Post;
import com.loqui.forum.entity.User;
import com.loqui.forum.repository.PostRepository;
import com.loqui.forum.service.ImageService;
import com.loqui.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Value("${upload.path}")
    private String uploadPath;

    private final PostService postService;
    private final ImageService imageService;

    @Autowired
    public PostController(PostService postService, ImageService imageService){
        this.postService = postService;
        this.imageService = imageService;
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
    public String save(@AuthenticationPrincipal User user,
                       @ModelAttribute("post") Post post,
                       @RequestParam("file") MultipartFile file) throws IOException {
        post.setDateCreate();
        post.setUser(user);
        postService.save(post);

        if(file != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadDir, resultFileName));

            Image image = new Image();
            image.setPath(uploadPath);
            image.setName(resultFileName);
            image.setPost(post);
            imageService.save(image);
        }

        return "redirect:/posts";
    }

}
