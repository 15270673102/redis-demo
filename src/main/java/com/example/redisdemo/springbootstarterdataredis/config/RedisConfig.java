package com.example.redisdemo.springbootstarterdataredis.config;


import com.example.redisdemo.springbootstarterdataredis.发布订阅.ReceiverRedisMessage;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.concurrent.CountDownLatch;

/**
 * redis工具类
 */

@Configuration
public class RedisConfig extends CachingConfigurerSupport {


    /**
     * 解决键、值序列化问题
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    /**
     * 创建连接工厂
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);

        //接受消息的key
        container.addMessageListener(listenerAdapter, new PatternTopic(RedisCons.topic));
        return container;
    }

    /**
     * 绑定消息监听者和接收监听的方法
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(ReceiverRedisMessage receiver) {
        return new MessageListenerAdapter(receiver, RedisCons.receiveMessage);
    }


    /**
     * 注册订阅者
     * @param latch
     * @return
     */
    @Bean
    public ReceiverRedisMessage receiver(CountDownLatch latch) {
        return new ReceiverRedisMessage(latch);
    }


    /**
     * 计数器，用来控制线程
     * @return
     */
    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(1);//指定了计数的次数 1
    }

}
