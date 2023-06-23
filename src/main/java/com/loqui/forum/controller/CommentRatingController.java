package com.loqui.forum.controller;

import com.loqui.forum.entity.Comment;
import com.loqui.forum.entity.CommentRating;
import com.loqui.forum.entity.Enum.RatingEnum;
import com.loqui.forum.entity.PostRating;
import com.loqui.forum.entity.User;
import com.loqui.forum.service.CommentRatingService;
import com.loqui.forum.service.CommentService;
import com.loqui.forum.service.PostRatingService;
import com.loqui.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/comments/{id}/rating")
public class CommentRatingController {

    private final CommentRatingService commentRatingService;
    private final CommentService commentService;

    @Autowired
    public CommentRatingController(CommentRatingService commentRatingService, CommentService commentService) {
        this.commentRatingService = commentRatingService;
        this.commentService = commentService;
    }

    @GetMapping("positive")
    public String addPositiveRating(@AuthenticationPrincipal User user, @PathVariable("id") Long commentId, @RequestParam Long postId) {
        CommentRating commentRatingOfCurrentUser = commentRatingService.findByUsernameAndPostId(commentId, user.getUsername());

        if(commentRatingOfCurrentUser == null){
            commentRatingService.addRating(commentService.findById(commentId).orElseThrow(),
                    user,
                    RatingEnum.LIKE);
            return "redirect:/posts/" + postId;
        }

        commentRatingService.changeRating(commentRatingOfCurrentUser, user, RatingEnum.LIKE);
        return "redirect:/posts/" + postId;
    }

    @GetMapping("negative")
    public String addNegativeRating(@AuthenticationPrincipal User user, @PathVariable("id") Long commentId, @RequestParam Long postId) {
        CommentRating commentRatingOfCurrentUser = commentRatingService.findByUsernameAndPostId(commentId, user.getUsername());

        if(commentRatingOfCurrentUser == null){
            commentRatingService.addRating(commentService.findById(commentId).orElseThrow(),
                    user,
                    RatingEnum.DISLIKE);
            return "redirect:/posts/" + postId;
        }

        commentRatingService.changeRating(commentRatingOfCurrentUser, user, RatingEnum.DISLIKE);
        return "redirect:/posts/" + postId;
    }

}
