package com.example.redisdemo.redissonDemo;

import cn.hutool.core.lang.Console;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

/**
 * @auther: wangjiayu
 * @date: 2020/1/15 10:16
 */
public class StringDemo {

    public static void main(String[] args) {
        RedissonClient redission = redissonUtil.getRedission();

        RBucket<Object> msg = redission.getBucket("msg");
        Console.log(msg.get());

        Console.log(redission.getBucket("msg1").get());

        Iterable<String> keys = redission.getKeys().getKeys();
        keys.forEach(System.out::println);
        redission.shutdown();
    }
}
