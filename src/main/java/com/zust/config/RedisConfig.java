package com.zust.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Configuration
public class RedisConfig extends CachingConfigurerSupport {


    /**
     * 自定义RedisTemplate
     * 设置Redis序列化方式，默认使用的是JDKSerializer的序列化方式，效率低，所以这里设置使用FastJsonRedisSerializer
     */
    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // 设置redis连接（LettuceConnectionFactory实现了RedisConnectionFactory）
        redisTemplate.setConnectionFactory(connectionFactory);

        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // key设置StringRedisSerializer序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // Hash key设置序列化
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        //value设置序列化
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //Hash value设置序列化
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());


        return redisTemplate;
    }

}