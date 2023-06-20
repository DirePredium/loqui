package com.loqui.forum.controller;

import com.loqui.forum.entity.*;
import com.loqui.forum.entity.Enum.RatingEnum;
import com.loqui.forum.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final UserService userService;
    @Value("${upload.path}")
    private String uploadPath;

    private final PostService postService;
    private final ImageService imageService;
    private final TopicService topicService;


    @Autowired
    public PostController(PostService postService, ImageService imageService, TopicService topicService, UserService userService){
        this.postService = postService;
        this.imageService = imageService;
        this.topicService = topicService;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model){
        List<Post> list = postService.findAll();
        model.addAttribute("posts", list);
        return "posts/posts";
    }

    @GetMapping("/{id}")
    public String showPost(@AuthenticationPrincipal User user, Model model, @PathVariable("id") Long id){
        Optional<Post> optionalPost = postService.findById(id);

        if(optionalPost.isEmpty()) {
            model.addAttribute("errorMessage", "Can`t define post");
            return "error";
        }
        model.addAttribute("post", optionalPost.get());

        user = userService.findByUsername(user.getUsername());

        model.addAttribute("user", user);

        model.addAttribute("enumRating", RatingEnum.class);
        return "posts/post";
    }

    @GetMapping("create")
    public String indexCreate(Model model){
        Post post = new Post();
        Topic topic = new Topic();
        topic.setTitle("autumn");
        post.setTopics(Set.of(topic));
        model.addAttribute("post", post);
        return "posts/post_create";
    }

    @PostMapping()
    public String save(@AuthenticationPrincipal User user,
                       @ModelAttribute("post") Post post,
                       @RequestParam(value = "file", required = false) MultipartFile[] files,
                       @RequestParam(value = "topic", required = false) String[] topicTitles) throws IOException {
        post.setDateCreate();
        post.setUser(user);
        if(topicTitles != null && topicTitles.length != 0){
            Set<Topic> topics = saveTopicsByTitles(topicTitles);
            post.setTopics(topics);
        }
        postService.save(post);

        if(files != null && files.length != 0){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            for (MultipartFile file : files) {
                if(file != null && !Objects.equals(file.getOriginalFilename(), "")){
                    String fileName = createUniqueFileName(file.getOriginalFilename());
                    file.transferTo(new File(uploadDir, fileName));
                    saveImageToDB(fileName, post);
                }
            }
        }

        return "redirect:/posts";
    }

    private Set<Topic> saveTopicsByTitles(String[] topicTitles) {
        Set<Topic> topics = new HashSet<>();
        for (String title : topicTitles) {
            Topic topic = topicService.findByTitle(title);
            if(topic == null){
                topic = new Topic();
                topic.setTitle(title);
                topicService.save(topic);
            }
            topics.add(topic);
        }
        return topics;
    }

    private void saveImageToDB(String resultFileName, Post post){
        Image image = new Image();
        image.setPath(uploadPath);
        image.setName(resultFileName);
        image.setPost(post);
        imageService.save(image);
    }

    private String createUniqueFileName(String originalFilename){
        String uuidFile = UUID.randomUUID().toString();
        return uuidFile + "." + originalFilename;
    }

}
