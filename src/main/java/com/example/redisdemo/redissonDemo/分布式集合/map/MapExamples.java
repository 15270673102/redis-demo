package com.example.redisdemo.redissonDemo.分布式集合.map;

import cn.hutool.core.lang.Console;
import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.api.map.event.EntryCreatedListener;
import org.redisson.api.map.event.EntryEvent;
import org.redisson.api.map.event.EntryExpiredListener;
import org.redisson.api.map.event.EntryRemovedListener;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * map
 * @auther: wangjiayu
 * @date: 2020/1/15 10:27
 */
public class MapExamples {

    public static void main(String[] args) throws InterruptedException {
        RedissonClient client = redissonUtil.getRedission();

        // 基本用法
        RMap<String, String> map = client.getMap("nameMap");
        map.clear();
        map.put("name", "yanglbme");
        map.put("address", "Shenzhen");
        map.put("link", "yanglbme - Overview");

        System.out.println("Map size: " + map.size());
        System.out.println("Is map contains key 'link': " + map.containsKey("link"));
        System.out.println("Value mapped by key 'name': " + map.get("name"));
        boolean added = map.putIfAbsent("link", "Welcome to join the Doocs Open Source organization") == null;
        System.out.println("Is value mapped by key 'link' added: " + added);

        //元素淘汰功能
        RMapCache<String, String> map1 = client.getMapCache("map1");
        map1.put("111", "111", 1L, TimeUnit.SECONDS);
        map1.put("222", "222", 3L, TimeUnit.SECONDS);
        Thread.sleep(2000L);
        map1.forEach((key, value) -> System.err.println(key + "--元素淘汰功能 --" + value));


        //映射监听器（Map Listener）
        RMapCache<String, String> map2 = client.getMapCache("map2");
        map2.addListener(new EntryCreatedListener() {
            @Override
            public void onCreated(EntryEvent event) {
                System.err.println(event);
            }
        });
        map2.addListener(new EntryExpiredListener() {
            @Override
            public void onExpired(EntryEvent event) {
                System.err.println("111 过期了");
            }
        });
        map2.addListener(new EntryRemovedListener() {
            @Override
            public void onRemoved(EntryEvent event) {
                System.err.println("元素 remove");
            }
        });
        map2.put("111", "111", 1L, TimeUnit.SECONDS);
        map2.put("333", "333", 11L, TimeUnit.SECONDS);
        map2.put("222", "222", 3L, TimeUnit.SECONDS);
        map2.remove("333");
        Thread.sleep(2000L);
        map2.forEach((key, value) -> System.err.println(key + " - 映射监听器 -" + value));

        client.shutdown();
    }
}
