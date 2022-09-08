package com.example.serviceuser.controller;


import com.example.serviceuser.service.RedisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class FollowController {
    @Resource
    private RedisService redisService;

    /**
     * 缓存关注
     * @param followedId
     * @param followerId
     */
    @GetMapping(value = "/follow/{followedId}/{followerId}")
    public void follow(@PathVariable ("followedId") String followedId,
                       @PathVariable ("followerId") String followerId){
        redisService.saveFollow(followedId,followerId);
    }

    /**
     * 删除关注缓存
     * @param followedId
     * @param followerId
     */
    @PostMapping(value = "/unfollow/{followedId}/{followerId}")
    public void unfollow(@PathVariable ("followedId") String followedId,
                         @PathVariable ("followerId") String followerId){
        redisService.unFollow(followedId,followerId);
    }

}
