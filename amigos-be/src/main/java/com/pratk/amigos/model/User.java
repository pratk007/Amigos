package com.pratk.amigos.model;

import com.pratk.amigos.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id = UUID.randomUUID().toString();

    private String username;
    private String name;
    private String email;
    private String mobile;
    private String password;
    private String website;
    private String bio;
    private Gender gender;
    private String image;

    @Embedded
    @ElementCollection
    private Set<UserDTO> followers = new HashSet<>();

    @Embedded
    @ElementCollection
    private Set<UserDTO> followings = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Story> stories = new ArrayList<>();

    @ManyToMany
    private List<Post> savedPosts = new ArrayList<>();

    public User() {}

    public User(String id, String username, String name, String email, String mobile, String password,
                String website, String bio, Gender gender, String image, Set<UserDTO> followers,
                Set<UserDTO> followings, List<Story> stories, List<Post> savedPosts) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.website = website;
        this.bio = bio;
        this.gender = gender;
        this.image = image;
        this.followers = followers;
        this.followings = followings;
        this.stories = stories;
        this.savedPosts = savedPosts;
    }

    public void setProperty(String field, Object value) {
        switch (field) {
            case "email":
                this.email = (String) value;
                break;
            case "name":
                this.name = (String) value;
                break;
            case "bio":
                this.bio = (String) value;
                break;
            case "username":
                this.username = (String) value;
                break;
            case "mobile":
                this.mobile = (String) value;
                break;
            case "gender":
                this.gender = (Gender) value;
                break;
            case "website":
                this.website = (String) value;
                break;
            case "image":
                this.image = (String) value;
                break;
            default:
                break;
        }
    }
}
