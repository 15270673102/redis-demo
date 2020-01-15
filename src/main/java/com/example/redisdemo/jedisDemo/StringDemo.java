package com.example.redisdemo.jedisDemo;

import cn.hutool.core.lang.Console;
import redis.clients.jedis.Jedis;

/**
 * @auther: wangjiayu
 * @date: 2020/1/15 10:02
 */
public class StringDemo {

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = jedisUtil.getJedis();
        jedis.set("name", "wangjaiyu");
        Console.log(jedis.get("name"));
        Console.log(jedis.exists("name"));

        jedis.set("name1", "1231321");
        jedis.expire("name1", 2);
        Thread.sleep(2100);
        Console.log(jedis.get("name1"));

    }

}
