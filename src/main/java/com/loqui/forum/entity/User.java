package com.loqui.forum.entity;

import com.loqui.forum.entity.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @Basic(optional = false)
    private Role role;
    @Basic(optional = false)
    private String email;
    @Basic(optional = false)
    private String nickname;
    @Basic(optional = false)
    private String password;
    @OneToMany(mappedBy="user")
    private Set<Post> posts;

}
