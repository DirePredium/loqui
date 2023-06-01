package com.loqui.forum.controller;

import com.loqui.forum.entity.PostRating;
import com.loqui.forum.entity.User;
import com.loqui.forum.exception.EntityNotFoundException;
import com.loqui.forum.service.PostRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/posts/{id}/rating")
public class PostRatingController {
    private final PostRatingService ratingManager;

    @Autowired
    public PostRatingController(PostRatingService ratingManager) {
        this.ratingManager = ratingManager;
    }

    @GetMapping("positive")
    public String changePositiveRating(@AuthenticationPrincipal User user, @PathVariable("id") Long postId) {
        PostRating postRating = getPostRatingByPostId(postId);
        if (!isUserRated(postRating, user)) {
            ratingManager.addPositiveRating(postRating, user);
        }
        return "redirect:/posts/" + postId;
    }

    @GetMapping("negative")
    public String changeNegativeRating(@AuthenticationPrincipal User user, @PathVariable("id") Long postId) {
        PostRating postRating = getPostRatingByPostId(postId);
        if (!isUserRated(postRating, user)) {
            ratingManager.addNegativeRating(postRating, user);
        }
        return "redirect:/posts/" + postId;
    }

    private boolean isUserRated(PostRating postRating, User user){
        Set<User> post_users = postRating.getUsers();
        return post_users.stream()
                .map(User::getUsername)
                .anyMatch(user.getUsername()::equals);
    }

    private PostRating getPostRatingByPostId(Long postId){
        Optional<PostRating> userOptional = ratingManager.findByPostId(postId);
        if(userOptional.isEmpty()){
            throw new EntityNotFoundException();
        }
        return userOptional.get();
    }

}
