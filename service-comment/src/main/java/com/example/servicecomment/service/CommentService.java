package com.example.servicecomment.service;


import com.example.common.utils.Result;
import com.example.common.entity.Comment;
import com.example.servicecomment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public Result add(Comment comment) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            commentMapper.add(comment);
            result.setMsg("添加成功");
            result.setSuccess(true);
            result.setDetail(comment);
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
            Comment existComment = commentMapper.findComById(id);
            if(existComment == null){
                result.setMsg("评论不存在");
            }else{
                commentMapper.delete(id);
                result.setMsg("删除成功");
                result.setSuccess(true);
                result.setDetail(existComment);
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result update(Comment comment) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            Comment existComment = commentMapper.findComById(comment.getId());
            if(existComment == null){
                result.setMsg("评论不存在");
            }else{
                commentMapper.update(comment);
                result.setMsg("更改成功");
                result.setSuccess(true);
                result.setDetail(comment);
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Comment view(String from_uid) {
        try{
            Comment existComment = commentMapper.findComByFrom(from_uid);
            if(existComment == null){
                return null;
            }else{
                return existComment;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

