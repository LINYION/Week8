package com.example.serviceuser.controller;

import com.example.common.utils.Result;
import com.example.serviceuser.entity.User;
import com.example.serviceuser.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/regist")
    public Result regist(@RequestBody User user){
        return userService.regist(user);
    }

    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }

    @GetMapping(value = "/view/{userName}")
    public Result view(@PathVariable ("userName") String userName){
        return userService.view(userName);
    }
}
