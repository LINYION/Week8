package com.example.servicecomment.service;

import com.example.common.utils.Result;
import com.example.servicecomment.entity.Reply;
import com.example.servicecomment.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    public Result add(Reply reply) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            replyMapper.add(reply);
            result.setMsg("添加成功");
            result.setSuccess(true);
            result.setDetail(reply);
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
            Reply existReply = replyMapper.findReplyById(id);
            if(existReply == null){
                result.setMsg("评论不存在");
            }else{
                replyMapper.delete(id);
                result.setMsg("删除成功");
                result.setSuccess(true);
                result.setDetail(existReply);
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result update(Reply reply) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            Reply existReply = replyMapper.findReplyById(reply.getId());
            if(existReply == null){
                result.setMsg("评论不存在");
            }else{
                replyMapper.update(reply);
                result.setMsg("更改成功");
                result.setSuccess(true);
                result.setDetail(reply);
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}