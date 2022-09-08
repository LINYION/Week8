package com.example.serviceuser.service;

import com.example.common.entity.*;
import com.example.common.utils.JWTUtils;
import com.example.common.utils.Result;
import com.example.serviceuser.entity.User;
import com.example.serviceuser.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    public Result regist(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            User existUser = userMapper.findUserByName(user.getUsername());
            if(existUser != null){
                //如果用户名已存在
                result.setMsg("用户名已存在");
            }else{
                userMapper.regist(user);
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 登录
     * @param user 用户名和密码
     * @return Result
     */
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Long userId= userMapper.login(user);
            if(userId == null){
                result.setMsg("用户名或密码错误");
            }else{
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(userId);
                Map<String, String> map = new HashMap<>();//用来存放payload
                map.put("id", String.valueOf(user.getId()));
                map.put("username",user.getUsername());
                user.setToken(JWTUtils.getToken(map));
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result view(String userName) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            User existUser = userMapper.findUserByName(userName);
            if(existUser == null){
                result.setMsg("用户不存在");

            }else{
                RestTemplate template = new RestTemplate();
                List<Object> UserList = new ArrayList<>();
                UserList.add(template.getForObject("http://localhost:8201/question/"+userName, Question.class));
                UserList.add(template.getForObject("http://localhost:8201/answer/view/"+userName, Answer.class));
                UserList.add(template.getForObject("http://localhost:8101/comment/"+ userName, Comment.class));
                result.setMsg("查找成功");
                result.setSuccess(true);
                result.setDetail(UserList);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}