package com.example.redisdemo.redissonDemo.分布式集合.queue;

import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;

public class Queue {

    public static void main(String[] args) {
        RedissonClient client = redissonUtil.getRedission();

        RQueue<Object> queue = client.getQueue("anyQueue");
        queue.clear();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.peek());
        System.out.println(queue.poll());

        queue.readAll().forEach(System.err::println);

        client.shutdown();
    }
}
