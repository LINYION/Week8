package com.example.serviceuser.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String token;
    private Integer folCount;

    public User(Long followedId, Integer value) {
        id = followedId;
        folCount = value;
    }
}