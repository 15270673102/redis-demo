package com.example.redisdemo.springbootstarterdataredis.发布订阅;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * 注入消息接受类 (1 -> 1 消费模式)
 * @auther: wangjiayu
 * @date: 2020/1/14 17:27
 */

@Slf4j
@Component
public class ReceiverRedisMessage {

    private CountDownLatch latch;

    @Autowired
    public ReceiverRedisMessage(CountDownLatch latch) {
        this.latch = latch;
        log.info("CountDownLatch --> {} ", latch.getCount());
    }

    /**
     * [源码介绍 -- > 多线程处理任务]
     * 队列消息接收方法
     * @param jsonMsg
     */
    public void receiveMessage(String jsonMsg) {
        log.info("[开始消费REDIS消息队列phone数据...]");
        try {
            System.out.println(jsonMsg);
            log.info("[消费REDIS消息队列phone数据成功.]");

        } catch (Exception e) {
            log.error("[消费REDIS消息队列phone数据失败，失败信息:{}]", e.getMessage());
        }
        latch.countDown();
    }


}
