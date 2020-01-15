package com.example.redisdemo.redissonDemo.分布式对象;

import cn.hutool.core.lang.Console;
import com.example.redisdemo.redissonDemo.redissonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.RBucket;
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
            Console.log(redission.getBucket("msg").get());
            Console.log(redission.getBucket("msg1").get());

            Iterable<String> keys = redission.getKeys().getKeys();
            keys.forEach(System.out::println);


            RBucket<User> user = redission.getBucket("User");
            Console.log(user.get().getName());
            user.set(new User("wnagjiayu", 25), 100, TimeUnit.SECONDS);
            Console.log(redission.getBucket("User").get());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redission.shutdown();
        }
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class User implements Serializable {
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
