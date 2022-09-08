package com.example.servicequestion.service;

import com.example.common.entity.Question;
import com.example.common.utils.Result;

import com.example.servicequestion.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public Result add(Question question) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            questionMapper.add(question);
            result.setMsg("添加成功");
            result.setSuccess(true);
            result.setDetail(question);
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result delect(Long id) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            Question existQuestion = questionMapper.findQueById(id);
            if(existQuestion == null){
                result.setMsg("问题不存在");
            }else{
                questionMapper.delete(id);
                result.setMsg("删除成功");
                result.setSuccess(true);
                result.setDetail(existQuestion);
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    public Result update(Question question) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            Question existQuestion = questionMapper.findQueById(question.getId());
            if(existQuestion == null){
                result.setMsg("问题不存在");
            }else{
                questionMapper.update(question);
                result.setMsg("更改成功");
                result.setSuccess(true);
                result.setDetail(question);
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Question view(String from_uid) {
        try{
            Question existQuestion = questionMapper.findQueByFrom(from_uid);
            if(existQuestion == null){
                return null;
            }else{
                return existQuestion;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
