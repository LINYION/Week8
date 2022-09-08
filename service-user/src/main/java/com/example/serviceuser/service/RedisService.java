package com.example.serviceuser.service;


import com.example.serviceuser.entity.Follow;
import com.example.serviceuser.entity.User;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * redis缓存操作
 */
@Service
@Transactional
public class RedisService {

    // 用户关注 KEY
    public static final String KEY_USER_FOLLOWER = "USER_FOLLOWER";

    // 用户关注数量 KEY
    public static final String KEY_USER_FOLLOWER_COUNT = "USER_FOLLOWER_COUNT";

    @Resource
    RedisTemplate redisTemplate;

    /**
     * 保存关注和用户关注量
     */
    public void saveFollow(String followedId, String followerId) {
            String folKey = getFolKey(followedId, followerId);
            redisTemplate.opsForHash().put(KEY_USER_FOLLOWER, folKey, 1);
            redisTemplate.opsForHash().increment(KEY_USER_FOLLOWER_COUNT, followedId, 1);
    }

    /**
     * 取消关注和用户关注量
     */
    public void unFollow(String followedId, String followerId) {
        String folKey = getFolKey(followedId, followerId);
        redisTemplate.opsForHash().put(KEY_USER_FOLLOWER, folKey, 0);
        redisTemplate.opsForHash().increment(KEY_USER_FOLLOWER_COUNT, followedId, -1);
    }

    /**
     * 清除关注数据的缓存
     */
    public void deleteFollow(List<Follow> list) {
        for (Follow follow : list) {
            String folKey = getFolKey(follow.getFollowed_id(), follow.getFollower_id());
            redisTemplate.opsForHash().delete(KEY_USER_FOLLOWER, folKey);
        }
    }

    /**
     * 删除被关注人数缓存
     */
    public void deleteFollowCount(String followedId) {
        redisTemplate.opsForHash().delete(KEY_USER_FOLLOWER_COUNT, followedId);
    }

    /**
     * 获取全部关注数据
     */
    public List<Follow> getAllLikeData() {
        List<Follow> list = new ArrayList<>();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(KEY_USER_FOLLOWER, ScanOptions.NONE);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String keys = entry.getKey().toString();
            String[] keyArr = keys.split("::");
            Follow follow = new Follow(keyArr[0], keyArr[1], (Integer) entry.getValue());
            list.add(follow);
        }
        return list;
    }

    /**
     * 获取用户关注量数据
     */
    public List<User> getUserFollowCount() {
        List<User> list = new ArrayList<>();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(KEY_USER_FOLLOWER_COUNT, ScanOptions.NONE);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String followedId = entry.getKey().toString();
            User user = new User( Long.parseLong(followedId), (Integer) entry.getValue());
            list.add(user);
        }
        return list;
    }

    /**
     * 拼接文章ID和点赞人ID作为key
     */
    private String getFolKey(String followedId, String followerId) {
        return new StringBuilder().append(followedId).append("::").append(followerId).toString();
    }
}