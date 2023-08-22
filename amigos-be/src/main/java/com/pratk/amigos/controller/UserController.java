package com.pratk.amigos.controller;

import com.pratk.amigos.exceptionhandling.UserException;
import com.pratk.amigos.model.User;
import com.pratk.amigos.response.MessageResponse;
import com.pratk.amigos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userId/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") String userId) throws UserException {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/request")
    public ResponseEntity<User> findUserProfile(@RequestHeader("Authorization") String token) throws UserException {
        return null;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) throws UserException {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/follow/{userId}")
    public ResponseEntity<MessageResponse> followUser(@PathVariable("userId") String followUserId) throws UserException {
        return null;
    }

    @PutMapping("/unfollow/{userId}")
    public ResponseEntity<MessageResponse> unfollowUser(@PathVariable("userId") String followUserId) throws UserException {
        return null;
    }

    @GetMapping("/search/{userIds}")
    public ResponseEntity<List<User>> findUserByIds(@PathVariable("userIds") List<String> userIds) throws UserException {
        List<User> users = userService.findUserByIds(userIds);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam("query") String query) throws UserException {
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUserDetails(@RequestHeader("Authorization") String token, @RequestBody User user)
                                                                                                throws UserException {
//        User updatedUser = userService.updateUserDetails(user, token);
        return null;
    }
}
