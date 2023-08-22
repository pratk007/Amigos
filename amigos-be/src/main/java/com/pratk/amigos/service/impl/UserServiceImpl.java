package com.pratk.amigos.service.impl;

import com.pratk.amigos.dto.UserDTO;
import com.pratk.amigos.exceptionhandling.UserException;
import com.pratk.amigos.mapper.UserMapper;
import com.pratk.amigos.model.User;
import com.pratk.amigos.repository.UserRepository;
import com.pratk.amigos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) throws UserException {
        verifyAllFields(user);
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
        if(byEmail.isPresent()){
            throw new UserException("Email already registered.");
        }
        if(byUsername.isPresent()){
            throw new UserException("Username already taken...");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(String userId) throws UserException {
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new UserException("User not found with id: " + userId);
    }

    @Override
    public User findUserProfile(String token) throws UserException {
        return null;
    }

    @Override
    public User findUserByUsername(String username) throws UserException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()){
            return byUsername.get();
        }
        throw new UserException("User not found with username: " + username);
    }

    @Override
    public String followUser(String requestUserId, String followUserId) throws UserException {
        User requestUser = findUserById(requestUserId);
        User followUser = findUserById(followUserId);
        UserDTO follower = UserMapper.convertEntityToDTO(requestUser);
        UserDTO following = UserMapper.convertEntityToDTO(followUser);
        requestUser.getFollowings().add(follower);
        followUser.getFollowers().add(following);
        userRepository.save(requestUser);
        userRepository.save(followUser);
        return "You have followed " + followUser.getUsername();
    }

    @Override
    public String unfollowUser(String requestUserId, String followUserId) throws UserException {
        User requestUser = findUserById(requestUserId);
        User followUser = findUserById(followUserId);
        UserDTO follower = UserMapper.convertEntityToDTO(requestUser);
        UserDTO following = UserMapper.convertEntityToDTO(followUser);
        requestUser.getFollowings().remove(follower);
        followUser.getFollowers().remove(following);
        userRepository.save(requestUser);
        userRepository.save(followUser);
        return "You have unfollowed " + followUser.getUsername();
    }

    @Override
    public List<User> findUserByIds(List<String> userIds) throws UserException {
        return userRepository.findAllUsersByUserIds(userIds);
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        List<User> users = userRepository.findByQuery(query);
        if(users.isEmpty()){
            throw new UserException("User not found");
        }
        return users;
    }

    @Override
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
//        if(updatedUser.getEmail() != null){
//            existingUser.setEmail(updatedUser.getEmail());
//        }
//        if(updatedUser.getName() != null){
//            existingUser.setName(updatedUser.getName());
//        }
//        if(updatedUser.getBio() != null){
//            existingUser.setBio(updatedUser.getBio());
//        }
//        if(updatedUser.getUsername() != null){
//            existingUser.setUsername(updatedUser.getUsername());
//        }
//        if(updatedUser.getMobile() != null){
//            existingUser.setMobile(updatedUser.getMobile());
//        }
//        if(updatedUser.getGender() != null){
//            existingUser.setGender(updatedUser.getGender());
//        }
//        if(updatedUser.getWebsite() != null){
//            existingUser.setWebsite(updatedUser.getWebsite());
//        }
//        if(updatedUser.getImage() != null){
//            existingUser.setImage(updatedUser.getImage());
//        }
//        if(updatedUser.getUserId().equals(existingUser.getUserId())){
//            return userRepository.save(existingUser);
//        }
//        throw new UserException("You cannot update this user details");
        Map<String, Object> updatedUserFields = new HashMap<>();
        updatedUserFields.put("email", updatedUser.getEmail());
        updatedUserFields.put("name", updatedUser.getName());
        updatedUserFields.put("bio", updatedUser.getBio());
        updatedUserFields.put("username", updatedUser.getUsername());
        updatedUserFields.put("mobile", updatedUser.getMobile());
        updatedUserFields.put("gender", updatedUser.getGender());
        updatedUserFields.put("website", updatedUser.getWebsite());
        updatedUserFields.put("image", updatedUser.getImage());
        for (Map.Entry<String, Object> field : updatedUserFields.entrySet()) {
            if (field.getValue() != null) {
                existingUser.setProperty(field.getKey(), field.getValue());
            }
        }
        if (existingUser.getId().equals(updatedUser.getId())) {
            return userRepository.save(existingUser);
        }
        throw new UserException("You cannot update this user details");
    }

    private void verifyAllFields(User user) throws UserException {
        if(user.getEmail()==null || user.getUsername()==null || user.getPassword()==null || user.getName()==null){
            throw new UserException("All fields are required.");
        }
    }
}
