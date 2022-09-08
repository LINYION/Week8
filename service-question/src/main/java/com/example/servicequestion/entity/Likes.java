package com.example.servicequestion.entity;

import lombok.Data;

@Data
public class Likes {

    // 文章id
    private String answer_id;

    // 点赞用户id
    private String user_id;

    // 点赞状态
    private Integer status;

    public Likes(String s, String s1, Integer value) {
        answer_id = s;
        user_id = s1;
        status = value;
    }
}