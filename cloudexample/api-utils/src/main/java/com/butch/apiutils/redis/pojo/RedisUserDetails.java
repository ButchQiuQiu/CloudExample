package com.butch.apiutils.redis.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 用户缓存，key为user_账号名 可追加属性，目前只有权限模块用到所以只有权限。
 */
public class RedisUserDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 拥有的权限
     */
    private String username;
    private List<String> authorities;

    public RedisUserDetails() {
    }

    public RedisUserDetails(String username, List<String> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "RedisUserDetails [authorities=" + authorities + ", username=" + username + "]";
    }

    

}