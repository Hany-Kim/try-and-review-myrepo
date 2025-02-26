package com.trillionares.tryit.auth.infrastructure.config;

import com.trillionares.tryit.auth.presentation.dto.responseDto.UserResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, UserResponseDto> userResponseDtoRedisTemplate(
      RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, UserResponseDto> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    template.setKeySerializer(RedisSerializer.string());
    template.setValueSerializer(RedisSerializer.json());
    return template;
  }

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName("localhost");
    jedisConnectionFactory.setPort(16379);
    return jedisConnectionFactory;
  }
}
