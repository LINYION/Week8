package com.example.servicequestion.service;

import com.example.common.utils.Result;
import com.example.common.entity.Answer;
import com.example.common.entity.Question;
import com.example.servicequestion.mapper.AnswerMapper;
import com.example.servicequestion.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class AnswerService {

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public Result add(Answer answer) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            answerMapper.add(answer);
            result.setMsg("添加成功");
            result.setSuccess(true);
            result.setDetail(answer);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result delete(Long question_id, Long answer_id) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Question existQuestion = questionMapper.findQueById(question_id);
            Answer existAnswer = answerMapper.findAnsById(answer_id);
            if (existQuestion == null || existAnswer == null || existAnswer.getQuestion_id() != question_id) {
                result.setMsg("不存在");
            } else {
                answerMapper.delete(answer_id);
                result.setMsg("删除成功");
                result.setSuccess(true);
                result.setDetail(existAnswer);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result update(Long question_id, Answer answer) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Question existQuestion = questionMapper.findQueById(question_id);
            Answer existAnswer = answerMapper.findAnsById(answer.getId());
            if (existQuestion == null || existAnswer == null || answer.getQuestion_id() != question_id) {
                result.setMsg("不存在");
            } else {
                answerMapper.update(answer);
                result.setMsg("更改成功");
                result.setSuccess(true);
                result.setDetail(answer);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Answer view(String from_uid) {
        try{
            Answer existAnswer = answerMapper.findAnsByFrom(from_uid);
            if(existAnswer == null){
                return null;
            }else{
                return existAnswer;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
