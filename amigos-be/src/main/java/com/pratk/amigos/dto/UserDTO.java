package com.pratk.amigos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String name;
    private String email;
    private String userImage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(username, userDTO.username) &&
                Objects.equals(name, userDTO.name) && Objects.equals(email, userDTO.email) &&
                Objects.equals(userImage, userDTO.userImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, email, userImage);
    }
}
