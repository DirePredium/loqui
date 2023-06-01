package com.loqui.forum.service;

import com.loqui.forum.entity.PostRating;
import com.loqui.forum.entity.User;
import com.loqui.forum.repository.PostRatingRepository;
import com.loqui.forum.service.Abstract.RatingService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PostRatingService extends RatingService<PostRating> {
    private final PostRatingRepository postRatingRepository;
    public PostRatingService(PostRatingRepository repository) {
        super(repository);
        this.postRatingRepository = repository;
    }

    public Optional<PostRating> findByPostId(Long postId) {
        return postRatingRepository.findByPostId(postId).stream().findFirst();
    }

    @Override
    public void addPositiveRating(PostRating ratingEntity, User user) {
        super.addPositiveRating(addUserToPostRating(ratingEntity, user));
    }

    @Override
    public void addNegativeRating(PostRating ratingEntity, User user) {
        super.addNegativeRating(addUserToPostRating(ratingEntity, user));
    }

    private PostRating addUserToPostRating(PostRating ratingEntity, User user){
        Set<User> users = ratingEntity.getUsers();
        users.add(user);
        ratingEntity.setUsers(users);
        return ratingEntity;
    }
}
