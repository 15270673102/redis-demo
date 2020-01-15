package com.example.redisdemo.发布订阅;

import com.example.redisdemo.config.RedisCons;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;


@SpringBootTest
class ReceiverRedisMessageTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void receiveMessage() {
        redisTemplate.convertAndSend(RedisCons.topic, "测试数据....1");
        redisTemplate.convertAndSend(RedisCons.topic, "测试数据....2");
        redisTemplate.convertAndSend(RedisCons.topic, "测试数据....3");
        redisTemplate.convertAndSend(RedisCons.topic, "测试数据....4");
        redisTemplate.convertAndSend(RedisCons.topic, "测试数据....5");
        redisTemplate.convertAndSend(RedisCons.topic, "测试数据....6");
    }

}