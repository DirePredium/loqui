package com.loqui.forum.repository;

import com.loqui.forum.entity.CommentRating;
import com.loqui.forum.entity.PostRating;
import com.loqui.forum.repository.Abstract.AbstractRatingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentRatingRepository extends AbstractRatingRepository<CommentRating, Long> {
    @Query("SELECT cr FROM CommentRating cr INNER JOIN cr.comment c INNER JOIN cr.user u WHERE c.id = ?1 AND u.username = ?2")
    CommentRating findByCommentIdAndUsername(Long commentId, String username);
}