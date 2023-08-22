package com.pratk.amigos.mapper;

import com.pratk.amigos.dto.UserDTO;
import com.pratk.amigos.model.User;

public class UserMapper {
    public static UserDTO convertEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserImage(user.getImage());
        return userDTO;
    }
}
