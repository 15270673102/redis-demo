package com.example.redisdemo.redissonDemo.分布式集合;

import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

/**
 * map
 * @auther: wangjiayu
 * @date: 2020/1/15 10:27
 */
public class MapExamples {

    public static void main(String[] args) {
        RedissonClient client = redissonUtil.getRedission();

        RMap<String, String> map = client.getMap("nameMap");
        map.put("name", "yanglbme");
        map.put("address", "Shenzhen");
        map.put("link", "yanglbme - Overview");

        boolean contains = map.containsKey("link");
        System.out.println("Map size: " + map.size());
        System.out.println("Is map contains key 'link': " + contains);

        String value = map.get("name");
        System.out.println("Value mapped by key 'name': " + value);
        boolean added = map.putIfAbsent("link", "Welcome to join the Doocs Open Source organization") == null;
        System.out.println("Is value mapped by key 'link' added: " + added);
        client.shutdown();
    }
}
