package com.butch.apiutils.redis.pojo;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * 用户缓存，key为user_账号名 可追加属性，目前只有权限模块用到所以只有权限。
 */
public class RedisUserDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 拥有的权限
     */
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public RedisUserDetails() {
    }

    public RedisUserDetails(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "RedisUserDetails [authorities=" + authorities + ", username=" + username + "]";
    }

    

}