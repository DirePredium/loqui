package com.loqui.forum.controller;

import com.loqui.forum.entity.Enum.RatingEnum;
import com.loqui.forum.entity.Post;
import com.loqui.forum.entity.PostRating;
import com.loqui.forum.entity.User;
import com.loqui.forum.exception.EntityNotFoundException;
import com.loqui.forum.service.PostRatingService;
import com.loqui.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/posts/{id}/rating")
public class PostRatingController {
    private final PostRatingService postRatingService;
    private final PostService postService;

    @Autowired
    public PostRatingController(PostRatingService postRatingService, PostService postService) {
        this.postRatingService = postRatingService;
        this.postService = postService;
    }

    @GetMapping("positive")
    public String addPositiveRating(@AuthenticationPrincipal User user, @PathVariable("id") Long postId) {
        PostRating postRatingOfCurrentUser = postRatingService.findByUsernameAndPostId(postId, user.getUsername());

        if(postRatingOfCurrentUser == null){
            postRatingService.addRating(postService.findById(postId).orElseThrow(),
                    user,
                    RatingEnum.LIKE);
            return "redirect:/posts/" + postId;
        }

        postRatingService.changeRating(postRatingOfCurrentUser, user, RatingEnum.LIKE);
        return "redirect:/posts/" + postId;
    }

    @GetMapping("negative")
    public String addNegativeRating(@AuthenticationPrincipal User user, @PathVariable("id") Long postId) {
        PostRating postRatingOfCurrentUser = postRatingService.findByUsernameAndPostId(postId, user.getUsername());

        if(postRatingOfCurrentUser == null){
            postRatingService.addRating(postService.findById(postId).orElseThrow(),
                    user,
                    RatingEnum.DISLIKE);
            return "redirect:/posts/" + postId;
        }

        postRatingService.changeRating(postRatingOfCurrentUser, user, RatingEnum.DISLIKE);
        return "redirect:/posts/" + postId;
    }

    private Optional<PostRating> getRateOfUser(List<PostRating> postRatingList, User user){
        return postRatingList.stream()
                .filter(postRating -> postRating.getUser()
                        .getUsername()
                        .equals(user.getUsername()))
                .findFirst();
    }

    private List<PostRating> getPostRatingListByPostId(Long postId){
        List<PostRating> postRatings = postRatingService.findByPostId(postId);
        return postRatings;
    }

}
