package com.loqui.forum.entity;

import com.loqui.forum.entity.Enum.RatingEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    @Basic(optional = false)
    private String text;
    @Basic(optional = false)
    @Column(name = "dateCreate")
    @Setter(AccessLevel.NONE)
    private LocalDate dateCreate;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable=false)
    private Post post;
    @ManyToOne
    @JoinColumn(name="parent_comment_id", nullable=true)
    private Comment parentComment;
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies = new ArrayList<>();
    @OneToMany(mappedBy="comment")
    private Set<CommentRating> rating;

    public void setDateCreate() {
        this.dateCreate = LocalDate.now();
    }

}