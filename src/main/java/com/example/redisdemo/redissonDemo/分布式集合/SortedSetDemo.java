package com.example.redisdemo.redissonDemo.分布式集合;

import cn.hutool.core.lang.Console;
import com.example.redisdemo.redissonDemo.redissonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;

/**
 * 有序集
 * @auther: wangjiayu
 * @date: 2020/1/15 14:39
 */
public class SortedSetDemo {

    public static void main(String[] args) {
        RedissonClient client = redissonUtil.getRedission();

        RSortedSet<Object> sortdSet = client.getSortedSet("sortdSet");
        sortdSet.clear();
        Console.log(sortdSet);

        // 新加入的值还是会排序的
        if (sortdSet.size() == 0) {
            sortdSet.add(3);
            sortdSet.add(1);
            sortdSet.add(5);
            sortdSet.add(4);
            sortdSet.add(11);
        } else {
            sortdSet.add(2);
        }
        Console.log(sortdSet);


        RSortedSet<Object> sortdSetObject = client.getSortedSet("sortdSet1");
        sortdSetObject.clear();
        sortdSetObject.add(new User("wangjaiyu", 12));
        sortdSetObject.add(new User("wangjaiyu", 1));
        sortdSetObject.add(new User("wangjaiyu", 1));
        sortdSetObject.add(new User("wangjaiyu", 3));
        Console.log(sortdSetObject);

        client.shutdown();
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class User implements Comparable<User> {
        private String name;
        private Integer age;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        // 类默认排序
        @Override
        public int compareTo(User o) {
            if (this.age.equals(o.age)) {
                return 0;
            }
            return (this.age > o.age) ? 1 : -1;
        }
    }


}
