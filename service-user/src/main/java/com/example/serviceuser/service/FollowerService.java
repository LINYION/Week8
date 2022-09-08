package com.example.serviceuser.service;

import com.example.serviceuser.entity.Follow;
import com.example.serviceuser.entity.User;
import com.example.serviceuser.mapper.FollowerDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 将redis现存的数据存进数据库
 */
@Service
@Transactional
public class FollowerService {

    @Resource
    private RedisService redisService;

    @Resource
    private FollowerDao followerDao;

    /**
     * 保存点赞数据到数据库
     */
    public void savaFollowData2DB() {
        List<Follow> followList = redisService.getAllLikeData();
        if (followList.size() > 0) {
            for (Follow follow : followList) {
                Follow newFollow = followerDao.getFollowList(follow);
                if (newFollow != null) {
                    newFollow.setStatus(follow.getStatus());
                    followerDao.updateFollow(newFollow);
                } else {
                    followerDao.saveFollow(follow);
                }
            }
            redisService.deleteFollow(followList);
        }
    }

    /**
     * 保存用户被关注数到数据库
     */
    public void saveUserFollowCount2DB() {
        List<User> userList = redisService.getUserFollowCount();
        if (userList.size() > 0) {
            for (User user : userList) {
                User userData = followerDao.getUserData(user.getId());
                if (userData != null) {
                    userData.setFolCount(userData.getFolCount() + user.getFolCount());
                    followerDao.updateUser(userData);
                } else {
                    followerDao.saveUser(user);
                }
                redisService.deleteFollowCount(String.valueOf(user.getId()));
            }
        }
    }
}