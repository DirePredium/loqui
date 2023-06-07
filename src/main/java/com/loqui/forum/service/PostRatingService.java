package com.loqui.forum.service;

import com.loqui.forum.entity.Enum.RatingEnum;
import com.loqui.forum.entity.Post;
import com.loqui.forum.entity.PostRating;
import com.loqui.forum.entity.User;
import com.loqui.forum.repository.PostRatingRepository;
import com.loqui.forum.service.Abstract.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostRatingService extends RatingService<PostRating> {
    private final PostRatingRepository postRatingRepository;
    public PostRatingService(PostRatingRepository repository) {
        super(repository);
        this.postRatingRepository = repository;
    }

    @Override
    public void changeRating(PostRating postRating, User user, RatingEnum ratingEnum) {
        if (postRating.getRating().equals(ratingEnum)) {
            postRatingRepository.delete(postRating);
            return;
        }
        postRating.setRating(ratingEnum);
        postRatingRepository.save(postRating);
    }

    @Override
    public void addRating(Post post, User user, RatingEnum ratingEnum) {
        PostRating postRating = new PostRating();
        postRating.setUser(user);
        postRating.setPost(post);
        postRating.setRating(ratingEnum);
        postRatingRepository.save(postRating);
    }

    public List<PostRating> findByPostId(Long postId) {
        return postRatingRepository.findByPostId(postId);
    }

    public long getLikeCount(Post post) {
        return postRatingRepository.countByPostIdAndRating(post.getId(), RatingEnum.LIKE);
    }

    public long getDislikeCount(Post post) {
        return postRatingRepository.countByPostIdAndRating(post.getId(), RatingEnum.DISLIKE);
    }

    public PostRating findByUsernameAndPostId(Long postId, String username){
        return postRatingRepository.findByPostIdAndUsername(postId, username);
    }
}
