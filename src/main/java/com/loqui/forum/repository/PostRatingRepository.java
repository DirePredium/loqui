package com.loqui.forum.repository;

import com.loqui.forum.entity.Enum.RatingEnum;
import com.loqui.forum.entity.PostRating;
import com.loqui.forum.entity.User;
import com.loqui.forum.repository.Abstract.AbstractRatingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRatingRepository extends AbstractRatingRepository<PostRating, Long> {
    List<PostRating> findByPostId(Long postId);

    long countByPostIdAndRating(long id, RatingEnum rating);


    @Query("SELECT pr FROM PostRating pr INNER JOIN pr.post p INNER JOIN pr.user u WHERE p.id = ?1 AND u.username = ?2")
    PostRating findByPostIdAndUsername(Long id, String username);
}
