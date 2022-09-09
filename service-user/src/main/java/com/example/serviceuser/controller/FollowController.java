package com.example.serviceuser.controller;


import com.example.serviceuser.service.RedisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("follow")
public class FollowController {
    @Resource
    private RedisService redisService;

    /**
     * 缓存关注
     * @param followedId
     * @param followerId
     */
    @GetMapping(value = "/save/{followedId}/{followerId}")
    public void save(@PathVariable ("followedId") String followedId,
                       @PathVariable ("followerId") String followerId){
        redisService.saveFollow(followedId,followerId);
    }

    /**
     * 删除关注缓存
     * @param followedId
     * @param followerId
     */
    @PostMapping(value = "/cancel/{followedId}/{followerId}")
    public void cancel(@PathVariable ("followedId") String followedId,
                         @PathVariable ("followerId") String followerId){
        redisService.unFollow(followedId,followerId);
    }

}
