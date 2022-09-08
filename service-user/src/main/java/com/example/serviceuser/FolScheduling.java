package com.example.serviceuser;

import com.example.serviceuser.service.FollowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class FolScheduling {

    private static Logger log = LoggerFactory.getLogger(FolScheduling.class);

    @Autowired
    private FollowerService followerService;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void likeCron() {
        log.info("Scheduled 定时任务.........开始.........");
        followerService.savaFollowData2DB();
        followerService.saveUserFollowCount2DB();
        log.info("Scheduled 定时任务.........结束.........");
    }
}