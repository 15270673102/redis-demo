package com.example.redisdemo.redissonDemo.分布式对象;

import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;


/**
 * 布隆过滤器
 */
public class BloomFilter {

    public static void main(String[] args) {
        RedissonClient client = redissonUtil.getRedission();

        RBloomFilter<Object> bloomfilter = client.getBloomFilter("bloomfilter");
        bloomfilter.tryInit(55000000L, 0.03);
        bloomfilter.add(1);
        bloomfilter.add(2);
        bloomfilter.add(3);
        bloomfilter.add(4);
        bloomfilter.add(5);
        bloomfilter.add(6);

        System.out.println(bloomfilter.contains(1));
        System.out.println(bloomfilter.contains(0));
        System.out.println(bloomfilter.count());
        System.out.println(bloomfilter.getSize());


        client.shutdown();
    }
}
