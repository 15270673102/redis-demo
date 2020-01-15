package com.example.redisdemo.jedisDemo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @auther: wangjiayu
 * @date: 2020/1/15 9:46
 */
public class jedisUtil {

    private static JedisPool pool;

    //静态代码块，初始化Redis池
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        pool = new JedisPool(config, "ohyy.xyz", 6379, 2000, null, 1);
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

}
