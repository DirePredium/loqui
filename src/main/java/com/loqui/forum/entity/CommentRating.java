package com.loqui.forum.entity;

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
public class CommentRating extends RatingEntity {
    @Basic(optional = false)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private Comment comment;
    @ManyToMany
    @JoinTable(name = "comment_rating_users",
            joinColumns = @JoinColumn(name = "comment_rating_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();
}