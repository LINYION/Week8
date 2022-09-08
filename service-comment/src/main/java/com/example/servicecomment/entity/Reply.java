package com.example.servicecomment.entity;

import lombok.Data;

@Data
public class Reply {
    //主键
    private Long id;
    //评论id
    private Long comment_id;
    //回复类型：表示回复的类型，因为回复可以是针对评论的回复(comment)，1
    //也可以是针对回复的回复(reply)，0通过这个字段来区分两种情景
    private boolean reply_type;
    //回复内容
    private String content;
    //回复用户id
    private String from_uid;
    //目标用户id
    private String to_uid;
}