package com.loqui.forum.repository;

import com.loqui.forum.entity.PostRating;
import com.loqui.forum.repository.Abstract.AbstractRatingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRatingRepository extends AbstractRatingRepository<PostRating, Long> {
    List<PostRating> findByPostId(Long postId);
}
