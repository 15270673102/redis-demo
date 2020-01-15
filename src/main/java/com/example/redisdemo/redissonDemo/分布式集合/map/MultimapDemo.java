package com.example.redisdemo.redissonDemo.分布式集合.map;

import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RListMultimap;
import org.redisson.api.RSetMultimap;
import org.redisson.api.RSetMultimapCache;
import org.redisson.api.RedissonClient;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 多值映射
 */
public class MultimapDemo {

    public static void main(String[] args) throws InterruptedException {
        RedissonClient client = redissonUtil.getRedission();

        //基于集（Set）的多值映射（Multimap）
        RSetMultimap<Object, Object> multimap = client.getSetMultimap("Multimap");
        multimap.clear();
        multimap.put("1", "111");
        multimap.put("1", "222");
        multimap.put("1", "222"); //重复
        multimap.put("1", "333");
        multimap.put("2", "111");
        multimap.put("2", "111");//重复
        multimap.entries().forEach(entry -> System.err.println(entry.getKey() + " -- " + entry.getValue()));

        //基于列表（List）的多值映射（Multimap）
        RListMultimap<Object, Object> multimap2 = client.getListMultimap("Multimap2");
        multimap2.clear();
        multimap2.put("1", "111");
        multimap2.put("1", "222");
        multimap2.put("1", "222");
        multimap2.put("1", "333");
        multimap2.put("2", "111");
        multimap2.put("2", "111");
        multimap2.entries().forEach(entry -> System.err.println(entry.getKey() + " -- " + entry.getValue()));

        //淘汰机制
        RSetMultimapCache<String, String> multimap3 = client.getSetMultimapCache("Multimap3");
        multimap3.put("1", "a");
        multimap3.put("1", "b");
        multimap3.put("1", "c");
        multimap3.put("2", "e");
        multimap3.put("2", "f");
        multimap3.expireKey("2", 1, TimeUnit.SECONDS);
        Thread.sleep(5000);
        multimap3.entries().forEach(entry -> System.err.println(entry.getKey() + " -- " + entry.getValue()));


        client.shutdown();
    }

}
