package com.example.redisdemo.redissonDemo.分布式对象;

import cn.hutool.core.lang.Console;
import com.example.redisdemo.redissonDemo.redissonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RBuckets;
import org.redisson.api.RedissonClient;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @auther: wangjiayu
 * @date: 2020/1/15 10:16
 */
public class BucketDemo {

    public static void main(String[] args) {
        RedissonClient redission = redissonUtil.getRedission();
        try {

            // 基本用法
            Console.log(redission.getBucket("msg").get());
            Console.log(redission.getBucket("msg1").get());
            redission.getKeys().getKeys().forEach(System.out::println);

            RBucket<User> user = redission.getBucket("User");
            user.set(new User("wnagjiayu", 25), 100, TimeUnit.SECONDS);
            Console.log(redission.getBucket("User").get());

            System.out.println("--------------- -------------");

            //多对象匹配
            redission.getBucket("Buckets1").set("Buckets1");
            redission.getBucket("Buckets2").set("Buckets2");
            redission.getBucket("Buckets3").set("Buckets3");
            RBuckets buckets = redission.getBuckets();
            buckets.find("Buckets*").forEach(System.err::println);
            buckets.get("Buckets3", "Buckets2", "Buckets3").forEach((k, v) -> System.err.println(k + "  " + v));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redission.shutdown();
        }
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class User {
        private String name;
        private Integer age;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
