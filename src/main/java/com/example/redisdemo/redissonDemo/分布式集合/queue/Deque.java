package com.example.redisdemo.redissonDemo.分布式集合.queue;

import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;

public class Deque {

    public static void main(String[] args) {
        RedissonClient redisson = redissonUtil.getRedission();

        RDeque<Object> queue = redisson.getDeque("anyDeque");
        queue.addFirst(1);
        queue.addLast(2);
        queue.addLast(2);
        queue.addLast(2);
        queue.removeFirst();
        queue.removeLast();
        queue.readAll().forEach(System.err::println);

        redisson.shutdown();
    }
}
