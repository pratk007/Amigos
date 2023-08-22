package com.pratk.amigos.model;

import com.pratk.amigos.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    private String commentId = UUID.randomUUID().toString();

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    @AttributeOverride(name = "email", column = @Column(name = "user_email"))
    private UserDTO userDTO;

    private String content;

    @Embedded
    @ElementCollection
    private Set<UserDTO> likedByUsers = new HashSet<>();

    private LocalDateTime createdAt;

    public Comment() {}

    public Comment(String commentId, UserDTO userDTO, String content, Set<UserDTO> likedByUsers, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.userDTO = userDTO;
        this.content = content;
        this.likedByUsers = likedByUsers;
        this.createdAt = createdAt;
    }
}
