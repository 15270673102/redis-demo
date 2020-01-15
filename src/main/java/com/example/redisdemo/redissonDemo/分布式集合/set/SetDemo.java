package com.example.redisdemo.redissonDemo.分布式集合.set;

import cn.hutool.core.lang.Console;
import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RSet;
import org.redisson.api.RSetCache;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 *  set
 * @auther: wangjiayu
 * @date: 2020/1/15 11:52
 */
public class SetDemo {


    public static void main(String[] args) throws InterruptedException {
        RedissonClient redission = redissonUtil.getRedission();

        // 基础用法
        RSet<Object> set = redission.getSet("set");
        set.add(1);
        set.add(2);
        set.add(3);

        Console.log(set.contains(1));
        Console.log(set.random());
        set.forEach(System.err::println);

        // set 内部元素缓存机制
        RSetCache<Object> setCache = redission.getSetCache("setCache");
        setCache.clear();
        setCache.add(1, 1L, TimeUnit.SECONDS);
        setCache.add(33, 3L, TimeUnit.SECONDS);
        setCache.add(44, 3L, TimeUnit.SECONDS);
        Thread.sleep(2000L);
        setCache.forEach(System.err::println);

        redission.shutdown();
    }
}
