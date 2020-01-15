package com.example.redisdemo.基础;

import cn.hutool.core.lang.Console;
import com.example.redisdemo.config.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BasicTests {

    @Resource
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        redisUtil.set("msg", "wangjiayu");
        Console.log(redisUtil.get("msg"));
    }

}
