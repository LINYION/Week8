package com.example.servicequestion.service;


import com.example.common.entity.Answer;
import com.example.servicequestion.entity.Likes;
import com.example.servicequestion.mapper.LikeDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LikeService {

    @Resource
    private RedisService redisService;

    @Resource
    private LikeDao likeDao;

    /**
     * 保存点赞数据到数据库
     */
    public void savaLikeData2DB() {
        List<Likes> likeList = redisService.getAllLikeData();

        if (likeList.size() > 0) {
            for (Likes like : likeList) {
                Likes likes = likeDao.getLikesList(like);
                if (likes != null) {
                    likes.setStatus(like.getStatus());
                    likeDao.updateLike(likes);
                } else {
                    likeDao.saveLike(like);
                }
            }
            redisService.deleteLike(likeList);
        }
    }

    /**
     * 保存文章点赞量到数据库，Redis不持久化文章点赞量
     */
    public void saveAnswerLikeCount2DB() {
        List<Answer> AnswerList = redisService.getAnswerLikeCount();

        if (AnswerList.size() > 0) {
            for (Answer answer : AnswerList) {
                Answer AnswerData = likeDao.getAnswerData(answer.getId());

                if (AnswerData != null) {
                    AnswerData.setLikeCount(AnswerData.getLikeCount() + answer.getLikeCount());
                    likeDao.updateAnswer(AnswerData);
                } else {
                    likeDao.saveAnswer(answer);
                }
                redisService.deleteLikeCount(String.valueOf(answer.getId()));
            }
        }
    }
}