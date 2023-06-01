package com.loqui.forum.entity;

import com.loqui.forum.entity.Abstract.RatingEntity;
import com.loqui.forum.entity.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(value = EnumType.STRING)
    @Basic(optional = false)
    private Set<Role> roles;
    @Basic(optional = false)
    private String username;
    @Basic(optional = false)
    private String password;
    private boolean active;

    @OneToMany(mappedBy="user")
    private Set<Post> posts;
    @OneToMany(mappedBy="user")
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "users")
    private Set<PostRating> postRatings = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<CommentRating> commentRatings = new HashSet<>();
}
