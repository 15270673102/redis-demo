package com.example.redisdemo.redissonDemo;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;

/**
 * @auther: wangjiayu
 * @date: 2020/1/15 10:37
 */
public class AtomicLongExamples {

    public static void main(String[] args) {
        RedissonClient client = redissonUtil.getRedission();

        RAtomicLong atomicLong = client.getAtomicLong("myLong");

        System.out.println("Init value: " + atomicLong.get());

        atomicLong.incrementAndGet();
        System.out.println("Current value: " + atomicLong.get());

        atomicLong.addAndGet(10L);
        System.out.println("Final value: " + atomicLong.get());

        client.shutdown();
    }
}
