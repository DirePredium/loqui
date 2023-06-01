package com.loqui.forum.entity;

import com.loqui.forum.entity.Abstract.RatingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class PostRating extends RatingEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToMany
    @JoinTable(name = "post_rating_users",
            joinColumns = @JoinColumn(name = "post_rating_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();
}