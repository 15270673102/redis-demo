package com.example.redisdemo.redissonDemo.分布式对象;

import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.*;

/**
 * 限流器
 */
public class RateLimiter {

    public static void main(String[] args) {

        RedissonClient client = redissonUtil.getRedission();

        RRateLimiter myRateLimiter = client.getRateLimiter("myRateLimiter");
        //最大流速 = 每1秒钟产生10个令牌
        myRateLimiter.trySetRate(RateType.OVERALL, 3, 1, RateIntervalUnit.SECONDS);

        new Thread(() -> {
            myRateLimiter.acquire(1);
            System.err.println("1111111");
        }).start();
        new Thread(() -> {
            myRateLimiter.acquire(1);
            System.err.println("1111111");
        }).start();
        new Thread(() -> {
            myRateLimiter.acquire(1);
            System.err.println("1111111");
        }).start();
        new Thread(() -> {
            myRateLimiter.acquire(1);
            System.err.println("1111111");
        }).start();

        client.shutdown();

    }
}
