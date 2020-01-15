package com.example.redisdemo.redissonDemo;

import cn.hutool.core.lang.Console;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

/**
 * @auther: wangjiayu
 * @date: 2020/1/15 10:20
 */
public class ListDemo {


    public static void main(String[] args) {
        RedissonClient redission = redissonUtil.getRedission();

        RList<Object> nameList = redission.getList("nameList");
        nameList.add("bingo");
        nameList.add("yanglbme");
        nameList.add("yanglbme - Overview");
        Console.log(nameList);

        nameList.remove(-1);

        boolean contains = nameList.contains("yanglbme");
        System.out.println("List size: " + nameList.size());
        System.out.println("Is list contains name 'yanglbme': " + contains);
        nameList.forEach(System.out::println);

        Console.log(redission.getList("nameList"));
        redission.shutdown();
    }
}
