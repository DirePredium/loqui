package com.loqui.forum.entity;

import jakarta.persistence.*;
import lombok.*;

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

}
