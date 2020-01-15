package com.example.redisdemo.redissonDemo;

import cn.hutool.core.lang.Console;
import org.redisson.api.RList;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;

/**
 * @auther: wangjiayu
 * @date: 2020/1/15 11:52
 */
public class SetDemo {


    public static void main(String[] args) {
        RedissonClient redission = redissonUtil.getRedission();

        RSet<Object> set = redission.getSet("set");
        set.add(1);
        set.add(2);
        set.add(3);

        Console.log(set.contains(1));
        Console.log(set.random());
        set.forEach(System.out::println);

        redission.shutdown();
    }
}
