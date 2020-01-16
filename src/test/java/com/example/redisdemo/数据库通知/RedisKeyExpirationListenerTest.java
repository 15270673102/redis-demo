package com.example.redisdemo.数据库通知;

import com.example.redisdemo.springbootstarterdataredis.config.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class RedisKeyExpirationListenerTest {

    @Resource
    private RedisUtil redisUtil;

    @Test
    void contextLoads() throws InterruptedException {
        redisUtil.set("demo:msg:123", "wangjiayu", 1);
        Thread.sleep(50000);
    }

}