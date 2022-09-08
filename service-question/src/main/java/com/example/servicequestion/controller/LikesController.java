package com.example.servicequestion.controller;

import com.example.servicequestion.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class LikesController {
    @Resource
    private RedisService redisService;

    @GetMapping(value = "/like/{AnswerId}/{userId}")
    public void like(@PathVariable ("AnswerId") String AnswerId,
                     @PathVariable ("userId") String userId){
        redisService.saveLike(AnswerId,userId);
    }

    @GetMapping(value = "/unlike/{AnswerId}/{userId}")
    public void unlike(@PathVariable ("AnswerId") String AnswerId,
                     @PathVariable ("userId") String userId){
        redisService.unLike(AnswerId,userId);
    }
}
