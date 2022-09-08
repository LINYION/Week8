package com.example.servicequestion.controller;

import com.example.common.utils.Result;
import com.example.common.entity.Answer;
import com.example.servicequestion.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class AnswerController {
    @Resource
    private AnswerService answerService;

    /**
     * 回答问题
     * @param answer 要添加的回答
     * @return
     */
    @PostMapping(value = "/question/{id_1}/answer/add")
    public Result add(@RequestBody Answer answer){
        return answerService.add(answer);
    }

    /**
     * 删除问题
     * @param question_id 问题id
     * @param answer_id  回答id
     * @return
     */
    @GetMapping(value = "/question/{id_1}/answer/delete/{id_2}")
    public Result delete(@PathVariable ("id_1") Long question_id,@PathVariable("id_2") Long answer_id){
        return answerService.delete(question_id,answer_id);
    }

    /**
     * 修改问题
     * @param question_id 问题id
     * @param answer 修改后的问题
     * @return
     */
    @PostMapping(value = "/question/{id_1}/answer/update")
    public Result update(@PathVariable ("id_1") Long question_id,@RequestBody Answer answer){
        return answerService.update(question_id,answer);
    }

    /**
     * 查看回答
     * @param
     * @param from_uid 回答id
     * @return
     */
    @GetMapping("answer/view/{from_uid}")
    public Answer view(@PathVariable("from_uid") String from_uid){
        return answerService.view(from_uid);
    }

}
