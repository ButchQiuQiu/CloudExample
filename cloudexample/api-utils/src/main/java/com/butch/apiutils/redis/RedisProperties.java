package com.butch.apiutils.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    /**
     * 过期时间
     */
    private Long user_expiration;

    public RedisProperties() {
    }

    public RedisProperties(Long user_expiration) {
        this.user_expiration = user_expiration;
    }

    public Long getUser_expiration() {
        return user_expiration;
    }

    public void setUser_expiration(Long user_expiration) {
        this.user_expiration = user_expiration;
    }

    @Override
    public String toString() {
        return "RedisProperties [user_expiration=" + user_expiration + "]";
    }
    
}