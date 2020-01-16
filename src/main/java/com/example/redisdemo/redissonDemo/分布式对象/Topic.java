package com.example.redisdemo.redissonDemo.分布式对象;

import com.example.redisdemo.redissonDemo.redissonUtil;
import org.redisson.api.RPatternTopic;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.api.listener.PatternMessageListener;
import org.redisson.api.listener.PatternStatusListener;

/**
 * @auther: wangjiayu
 * @date: 2020/1/16 17:28
 */
public class Topic {

    public static void main(String[] args) throws InterruptedException {
        RedissonClient client = redissonUtil.getRedission();

        //订阅分发
        RTopic<Object> topic = client.getTopic("topic");
        topic.addListener(new MessageListener<Object>() {
            @Override
            public void onMessage(CharSequence channel, Object msg) {
                System.err.println(channel.toString() + "收到数据了" + msg);
            }
        });
        Thread.sleep(400L);
        topic.publish("发消息了");
        topic.publish("发消息了");
        topic.publish("发消息了");
        topic.publish("发消息了");
        topic.publish("发消息了");

        // 模糊话题
        RPatternTopic<Object> topic1 = client.getPatternTopic("topic.*");
        topic1.addListener(new PatternMessageListener<Object>() {
            @Override
            public void onMessage(CharSequence pattern, CharSequence channel, Object msg) {
                System.err.println(pattern.toString() + "  " + channel.toString() + "收到数据了" + msg);
            }
        });

        client.getTopic("topic.rr").publish("topic2 发消息了");
        client.getTopic("topic.xx").publish("topic2 发消息了");
        client.getTopic("topic.ee").publish("topic2 发消息了");

        Thread.sleep(400L);

        client.shutdown();
    }
}
