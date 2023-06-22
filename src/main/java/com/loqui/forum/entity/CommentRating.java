package com.loqui.forum.entity;

import com.loqui.forum.entity.Abstract.Rating;
import com.loqui.forum.entity.Abstract.RatingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRating extends Rating {
    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}