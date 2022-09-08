package com.example.common.entity;

import lombok.Data;

@Data
public class Question {
    //主键
    private Long id;
    //发问用户名字
    private String from_uid;
    //问题标题
    private String title;
    //问题内容
    private String content;
}
