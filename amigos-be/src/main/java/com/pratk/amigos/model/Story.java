package com.pratk.amigos.model;

import com.pratk.amigos.dto.UserDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "stories")
public class Story {
    @Id
    private String storyId = UUID.randomUUID().toString();

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    @AttributeOverride(name = "email", column = @Column(name = "user_email"))
    private UserDTO userDTO;

    @NotNull
    private String image;
    private String caption;
    private LocalDateTime createdAt;

    public Story() {}

    public Story(String storyId, UserDTO userDTO, String image, String caption, LocalDateTime createdAt) {
        this.storyId = storyId;
        this.userDTO = userDTO;
        this.image = image;
        this.caption = caption;
        this.createdAt = createdAt;
    }
}
