package com.example.redisdemo.redissonDemo.分布式集合;

import cn.hutool.core.lang.Console;
import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;

/**
 *  set
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
