package com.example.common.entity;

import lombok.Data;

@Data

public class Comment {
    //主键
    private Long id;
    //回答id
    private Long answer_id;
    //评论内容
    private String content;
    //评论用户名字
    private String from_uid;
}
