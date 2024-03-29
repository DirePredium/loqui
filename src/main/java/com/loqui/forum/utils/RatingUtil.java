package com.loqui.forum.utils;

import com.loqui.forum.entity.Abstract.RatingEntity;
import com.loqui.forum.entity.Comment;
import com.loqui.forum.entity.Enum.RatingEnum;
import com.loqui.forum.entity.Post;
import com.loqui.forum.entity.User;

public class RatingUtil {

    public static long countPostRating(Post post, RatingEnum ratingEnum) {
        return post.getRating().stream()
                .filter(pr -> pr.getRating().equals(ratingEnum)).count();
    }
    public static boolean isRatedPostByUser(Post post, User user, RatingEnum ratingEnum) {
        return post.getRating().stream()
                .filter(pr -> pr.getUser().getUsername().equals(user.getUsername()))
                .anyMatch(pr -> pr.getRating().equals(ratingEnum));
    }

    public static long countCommentRating(Comment comment, RatingEnum ratingEnum) {
        return comment.getRating().stream()
                .filter(pr -> pr.getRating().equals(ratingEnum)).count();
    }

    public static boolean isRatedCommentByUser(Comment comment, User user, RatingEnum ratingEnum) {
        return comment.getRating().stream()
                .filter(pr -> pr.getUser().getUsername().equals(user.getUsername()))
                .anyMatch(pr -> pr.getRating().equals(ratingEnum));
    }

}
