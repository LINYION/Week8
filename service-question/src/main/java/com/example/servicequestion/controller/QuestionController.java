package com.example.servicequestion.controller;

import com.example.common.utils.Result;
import com.example.common.entity.Question;
import com.example.servicequestion.service.QuestionService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    /**
     * 提出问题
     * @param question
     * @return
     */
    @PostMapping(value = "/add")
    public Result add(@RequestBody Question question){
        return questionService.add(question);
    }

    /**
     *删除问题
     * @param id
     * @return
     */
    @GetMapping("/delect/{id}")
    public Result delect(@PathVariable("id") Long id){
        return questionService.delect(id);
    }

    /**
     * 修改问题
     * @param question
     * @return
     */
    @PostMapping(value = "/update")
    public Result update(@RequestBody Question question){
        return questionService.update(question);
    }

    /**
     * 查看问题
     * @param from_uid
     * @return
     */
    @GetMapping("/{from_uid}")
    public Question view(@PathVariable("from_uid") String from_uid){
        return questionService.view(from_uid);
    }

}