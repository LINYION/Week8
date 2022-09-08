package com.example.servicecomment.controller;

import com.example.common.utils.Result;
import com.example.servicecomment.entity.Reply;
import com.example.servicecomment.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    /**
     * 评论回答
     * @param reply
     * @return
     */
    @PostMapping(value = "/add")
    public Result add(@RequestBody Reply reply){
        return replyService.add(reply);
    }

    /**
     *删除评论
     * @param id
     * @return
     */
    @GetMapping("/delect/{id}")
    public Result delect(@PathVariable("id") Long id){
        return replyService.delect(id);
    }

    /**
     * 修改回复
     * @param reply
     * @return
     */
    @PostMapping(value = "/update")
    public Result update(@RequestBody Reply reply){
        return replyService.update(reply);
    }

}

