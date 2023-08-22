package com.pratk.amigos.service;

import com.pratk.amigos.exceptionhandling.UserException;
import com.pratk.amigos.model.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user) throws UserException;

    public User findUserById(String userId) throws UserException;

    public User findUserProfile(String token) throws UserException;

    public User findUserByUsername(String username) throws UserException;

    public String followUser(String requestUserId, String followUserId) throws UserException;

    public String unfollowUser(String requestUserId, String followUserId) throws UserException;

    public List<User> findUserByIds(List<String> userIds) throws UserException;

    public List<User> searchUser(String query) throws UserException;

    public User updateUserDetails(User updatedUser, User existingUser) throws UserException;
}
