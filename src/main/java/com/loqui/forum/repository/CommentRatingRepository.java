package com.loqui.forum.repository;

import com.loqui.forum.entity.CommentRating;
import com.loqui.forum.repository.Abstract.AbstractRatingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentRatingRepository extends AbstractRatingRepository<CommentRating, Long> {
}