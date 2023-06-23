package com.loqui.forum.entity;

import com.loqui.forum.entity.Abstract.RatingEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post extends RatingEntity {
    @Basic(optional = false)
    private String title;
    @Basic(optional = false)
    @Column(columnDefinition = "TEXT")
    private String contain;
    @Basic(optional = false)
    @Column(name = "dateCreate")
    @Setter(AccessLevel.NONE)
    private LocalDate dateCreate;
    @OneToMany(mappedBy="post")
    private Set<Image> images;
    @OneToMany(mappedBy="post")
    private Set<PostRating> rating;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToMany
    @JoinTable(
            name = "post_topic",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    Set<Topic> topics;

    public void setDateCreate() {
        this.dateCreate = LocalDate.now();
    }
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

}
