package com.example.servicequestion.controller;

import com.example.servicequestion.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("like")
public class LikesController {
    @Resource
    private RedisService redisService;

    @GetMapping(value = "/save/{AnswerId}/{userId}")
    public void save(@PathVariable ("AnswerId") String AnswerId,
                     @PathVariable ("userId") String userId){
        redisService.saveLike(AnswerId,userId);
    }

    @GetMapping(value = "/cancel/{AnswerId}/{userId}")
    public void cancel(@PathVariable ("AnswerId") String AnswerId,
                     @PathVariable ("userId") String userId){
        redisService.unLike(AnswerId,userId);
    }
}
