package com.example.serviceuser.entity;

import lombok.Data;

@Data
public class Follow {

    //被关注的人
    private String followed_id;
    //关注的人
    private String follower_id;
    //关注的状态
    private Integer status;

    public Follow(String followed_id, String follower_id, Integer status) {
        this.followed_id = followed_id;
        this.follower_id = follower_id;
        this.status = status;
    }
}