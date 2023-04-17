package com.loqui.forum.controller;

import com.loqui.forum.entity.Comment;
import com.loqui.forum.entity.Post;
import com.loqui.forum.entity.User;
import com.loqui.forum.service.CommentService;
import com.loqui.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("{postId}")
    private String save(@AuthenticationPrincipal User user, @PathVariable Long postId,
                        @RequestParam String text,
                        @RequestParam(required = false) Long commentId,
                        Model model
                        ){
        Optional<Post> optionalPost = postService.findById(postId);

        if(optionalPost.isEmpty()) {
            model.addAttribute("errorMessage", "Can`t define post");
            return "error";
        }
        Comment comment = new Comment();
        comment.setPost(optionalPost.get());
        comment.setUser(user);
        comment.setText(text);
        if(commentId != null){
            Optional<Comment> optionalComment = commentService.findById(commentId);

            if(optionalComment.isEmpty()) {
                model.addAttribute("errorMessage", "Can`t define comment");
                return "error";
            }
            comment.setParentComment(optionalComment.get());
        }
        comment.setDateCreate();
        commentService.save(comment);
        return "redirect:/posts/" + postId;
    }
}
