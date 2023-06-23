package com.loqui.forum.service;

import com.loqui.forum.entity.Comment;
import com.loqui.forum.entity.CommentRating;
import com.loqui.forum.entity.Enum.RatingEnum;
import com.loqui.forum.entity.PostRating;
import com.loqui.forum.entity.User;
import com.loqui.forum.repository.CommentRatingRepository;
import com.loqui.forum.service.Abstract.RatingService;
import org.springframework.stereotype.Service;

@Service
public class CommentRatingService extends RatingService<CommentRating, Comment> {

    private final CommentRatingRepository commentRatingRepository;
    public CommentRatingService(CommentRatingRepository repository) {
        super(repository);
        this.commentRatingRepository = repository;
    }

    @Override
    public void changeRating(CommentRating commentRating, User user, RatingEnum ratingEnum) {
        if (commentRating.getRating().equals(ratingEnum)) {
            commentRatingRepository.delete(commentRating);
            return;
        }
        commentRating.setRating(ratingEnum);
        commentRatingRepository.save(commentRating);
    }

    @Override
    public void addRating(Comment comment, User user, RatingEnum ratingEnum) {
        CommentRating commentRating = new CommentRating();
        commentRating.setUser(user);
        commentRating.setComment(comment);
        commentRating.setRating(ratingEnum);
        commentRatingRepository.save(commentRating);
    }

    public CommentRating findByUsernameAndPostId(Long commentId, String username){
        return commentRatingRepository.findByCommentIdAndUsername(commentId, username);
    }

}
