package com.example.redisdemo.redissonDemo.分布式集合.set;

import cn.hutool.core.lang.Console;
import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RLexSortedSet;
import org.redisson.api.RedissonClient;

import java.util.Iterator;
import java.util.Spliterator;

/**
 * 字典排序集  源码--》 ( RSortedSet<String>) 这个是一个string 类型的集合
 * @auther: wangjiayu
 * @date: 2020/1/15 15:45
 */
public class LexSortedSetDemo {

    public static void main(String[] args) {
        RedissonClient client = redissonUtil.getRedission();

        RLexSortedSet lexSortedSet = client.getLexSortedSet("LexSortedSet");
        lexSortedSet.clear();

        lexSortedSet.add("z");
        lexSortedSet.addAsync("c");
        lexSortedSet.add("b");
        lexSortedSet.add("1");
        lexSortedSet.add("9");

        Console.log(lexSortedSet.rank("z"));
        lexSortedSet.forEach(System.err::println);

        client.shutdown();
    }
}
