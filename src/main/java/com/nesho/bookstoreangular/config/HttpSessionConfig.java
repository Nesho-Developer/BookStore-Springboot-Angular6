package com.nesho.bookstoreangular.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class HttpSessionConfig {
    @Bean
    public LettuceConnectionFactory connectionFactory() {

        return new LettuceConnectionFactory();
    }
}
