package com.example.servicequestion;

import com.example.servicequestion.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LikeScheduling {
    private static Logger log = LoggerFactory.getLogger(LikeScheduling.class);
    @Resource
    private LikeService likeService;
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void likeCron() {
        log.info("Scheduled 定时任务.........开始.........");
        likeService.savaLikeData2DB();
        likeService.saveAnswerLikeCount2DB();
        log.info("Scheduled 定时任务.........结束.........");
    }
}
