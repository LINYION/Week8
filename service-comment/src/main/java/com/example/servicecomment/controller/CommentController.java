package com.example.servicecomment.controller;

import com.example.common.utils.Result;
import com.example.common.entity.Comment;
import com.example.servicecomment.service.CommentService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    /**
     * 评论回答
     * @param comment
     * @return
     */
    @PostMapping(value = "/add")
    public Result add(@RequestBody Comment comment){
        return commentService.add(comment);
    }

    /**
     *删除评论
     * @param id
     * @return
     */
    @GetMapping("/delect/{id}")
    public Result delect(@PathVariable("id") Long id){
        return commentService.delect(id);
    }

    /**
     * 修改评论
     * @param comment
     * @return
     */
    @PostMapping(value = "/update")
    public Result update(@RequestBody Comment comment){
        return commentService.update(comment);
    }

    @GetMapping("/{from_uid}")
    public Comment view(@PathVariable("from_uid") String from_uid){
        return commentService.view(from_uid);
    }

}

