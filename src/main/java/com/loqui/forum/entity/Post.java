package com.loqui.forum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.AccessLevel;

import java.time.LocalDate;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    private String title;
    @Basic(optional = false)
    @Column(columnDefinition = "TEXT")
    private String contain;
    @Basic(optional = false)
    @Column(name = "dateCreate")
    @Setter(AccessLevel.NONE)
    private LocalDate dateCreate;
    private Rate rate;
    @OneToMany(mappedBy="post")
    private Set<Image> images;
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
