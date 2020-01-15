package com.example.redisdemo.redissonDemo;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * @auther: wangjiayu
 * @date: 2020/1/15 10:33
 */
public class LockExamples {

    public static void main(String[] args) throws InterruptedException {
        RedissonClient client = redissonUtil.getRedission();

        RLock lock = client.getLock("lock");
        lock.lock();
        System.out.println("lock acquired");

        Thread t = new Thread(() -> {
            RLock lock1 = client.getLock("lock");
            lock1.lock();
            System.out.println("lock acquired by thread");
            lock1.unlock();
            System.out.println("lock released by thread");
        });

        t.start();
        t.join(1000);

        lock.unlock();
        System.out.println("lock released");

        t.join();

        client.shutdown();
    }
}
