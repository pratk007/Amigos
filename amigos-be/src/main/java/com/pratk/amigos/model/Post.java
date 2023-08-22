package com.pratk.amigos.model;

import com.pratk.amigos.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    private String postId = UUID.randomUUID().toString();

    private String caption;
    private String image;
    private String location;
    private String music;

    private LocalDateTime createdAt;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    @AttributeOverride(name = "email", column = @Column(name = "user_email"))
    private UserDTO userDTO;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @Embedded
    @ElementCollection
    @JoinTable(name = "liked_by_users", joinColumns = @JoinColumn(name = "user_id"))
    private Set<UserDTO> likedByUsers = new HashSet<>();

    public Post() {}

    public Post(String postId, String caption, String image, String location, String music,
                LocalDateTime createdAt, UserDTO userDTO, List<Comment> comments, Set<UserDTO> likedByUsers) {
        this.postId = postId;
        this.caption = caption;
        this.image = image;
        this.location = location;
        this.music = music;
        this.createdAt = createdAt;
        this.userDTO = userDTO;
        this.comments = comments;
        this.likedByUsers = likedByUsers;
    }
}
