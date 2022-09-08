package com.example.common.entity;

import lombok.Data;

@Data
public class Answer {
    //主键
    private Long id;
    //问题id
    private Long question_id;
    //回答用户名字
    private String from_uid;
    //回答内容
    private String content;
    //回答点赞数
    private Integer LikeCount;

    public Answer(Long answer_id, Integer value) {
        id = answer_id;
        LikeCount = value;
    }
}
