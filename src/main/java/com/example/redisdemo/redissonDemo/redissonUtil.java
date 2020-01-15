package com.example.redisdemo.redissonDemo;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @auther: wangjiayu
 * @date: 2020/1/15 10:13
 */
public class redissonUtil {

    private static RedissonClient redissonClient;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://ohyy.xyz:6379").setDatabase(1);
        redissonClient = Redisson.create(config);
    }

    public static RedissonClient getRedission() {
        return redissonClient;
    }

}
