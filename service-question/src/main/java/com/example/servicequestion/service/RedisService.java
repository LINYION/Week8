package com.example.servicequestion.service;


import com.example.common.entity.Answer;
import com.example.servicequestion.entity.Likes;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class RedisService {

    // 回答点赞 KEY
    public static final String KEY_ANSWER_LIKE = "ANSWER_LIKE";

    // 回答点赞数量 KEY
    public static final String KEY_ANSWER_LIKE_COUNT = "ANSWER_LIKE_COUNT";

    @Resource
    RedisTemplate redisTemplate;

    /**
     * 保存点赞和文章点赞量
     */
    public void saveLike(String AnswerId, String userId) {
        String likeKey = getLikeKey(AnswerId, userId);
        redisTemplate.opsForHash().put(KEY_ANSWER_LIKE, likeKey, 1);
        redisTemplate.opsForHash().increment(KEY_ANSWER_LIKE_COUNT, AnswerId, 1);
    }

    /**
     * 取消点赞和文章点赞量
     */
    public void unLike(String AnswerId, String userId) {
        String likeKey = getLikeKey(AnswerId, userId);
        redisTemplate.opsForHash().put(KEY_ANSWER_LIKE, likeKey, 0);
        redisTemplate.opsForHash().increment(KEY_ANSWER_LIKE_COUNT, AnswerId, -1);
    }

    /**
     * 删除点赞数据
     */
    public void deleteLike(List<Likes> list) {
        for (Likes like : list) {
            String likeKey = getLikeKey(like.getAnswer_id(), like.getUser_id());
            redisTemplate.opsForHash().delete(KEY_ANSWER_LIKE, likeKey);
        }
    }

    /**
     * 删除文章点赞量数据
     */
    public void deleteLikeCount(String AnswerId) {
        redisTemplate.opsForHash().delete(KEY_ANSWER_LIKE_COUNT, AnswerId);
    }

    /**
     * 获取全部点赞数据
     */
    public List<Likes> getAllLikeData() {
        List<Likes> list = new ArrayList<>();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(KEY_ANSWER_LIKE, ScanOptions.NONE);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String keys = entry.getKey().toString();
            String[] keyArr = keys.split("::");
            Likes like = new Likes( keyArr[0],keyArr[1], (Integer) entry.getValue());
            list.add(like);
        }
        return list;
    }

    /**
     * 获取文章点赞量数据
     */
    public List<Answer> getAnswerLikeCount() {
        List<Answer> list = new ArrayList<>();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(KEY_ANSWER_LIKE_COUNT, ScanOptions.NONE);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String AnswerId = entry.getKey().toString();
            Answer Answer = new Answer( Long.parseLong(AnswerId), (Integer) entry.getValue());
            list.add(Answer);
        }
        return list;
    }

    /**
     * 拼接文章ID和点赞人ID作为key
     */
    private String getLikeKey(String AnswerId, String userId) {
        return new StringBuilder().append(AnswerId).append("::").append(userId).toString();
    }
}