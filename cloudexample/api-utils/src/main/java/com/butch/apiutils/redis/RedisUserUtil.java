package com.butch.apiutils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis的工具类，
 */
@Component
public class RedisUserUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 给redis添加所有用户。
     */
    public void initAllUser(){
        redisTemplate.opsForValue();
    }

    /**
     * 让redis删除所有用户。
     */
    public void deletAllUser(){

    }

    /**
     * 更新所有的用户,原则上时删除后再添加。
     */
    public void updateAllUser(){
        this.deletAllUser();
        this.initAllUser();
    }
}