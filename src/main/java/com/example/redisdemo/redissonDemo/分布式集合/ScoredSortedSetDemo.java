package com.example.redisdemo.redissonDemo.分布式集合;

import cn.hutool.core.lang.Console;
import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;

/**
 * 计分排序集
 * @auther: wangjiayu
 * @date: 2020/1/15 15:11
 */
public class ScoredSortedSetDemo {

    public static void main(String[] args) {
        RedissonClient client = redissonUtil.getRedission();

        RScoredSortedSet<Object> scoredSortedSet = client.getScoredSortedSet("ScoredSortedSet");
        scoredSortedSet.clear();
        scoredSortedSet.add(4, "xxxx");
        scoredSortedSet.add(2, "xxxx3");
        scoredSortedSet.add(1, "xxxx2");
        scoredSortedSet.add(111, "xxxx1");

        Console.log(scoredSortedSet);
        Console.log(scoredSortedSet.first());
        Console.log(scoredSortedSet.firstScore());
        Console.log(scoredSortedSet.size());

        Console.log(scoredSortedSet.rank("xxxx"));

        client.shutdown();
    }
}
