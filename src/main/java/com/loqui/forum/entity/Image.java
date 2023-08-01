package com.loqui.forum.entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    private String path;
    @Basic(optional = false)
    private String name;
    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;
}
