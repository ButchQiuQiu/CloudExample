package com.butch.apiutils.redis;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.butch.apiutils.jwt.JwtTokenUtil;
import com.butch.apiutils.pojo.User;
import com.butch.apiutils.redis.pojo.RedisUserDetails;

/**
 * Redis的工具类，
 */
@Component
public class RedisUserUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 按照UserDetail存储登录缓存,key为user_username,value目前只有RedisUserDetails的权限.
     * 这里不能强制转换，只能一个一个翻译转换否则会json序列化失败。因为SimpleGrantedAuthority没有空构造器。
     * 存在时间为yaml中设置
     */
    @SuppressWarnings("all")
    public void setRedisUserByUser(User user) {
        redisTemplate.opsForValue().set("user_" + user.getUsername(),
                new RedisUserDetails(user.getUsername(),user.getAuthorities()), redisProperties.getUser_expiration(),
                TimeUnit.SECONDS);
    }

    /**
     * 按照user_username获取到对应的RedisUserDetails
     */
    public RedisUserDetails getUserDetailsByUserId(String tokenName) {
        return (RedisUserDetails) redisTemplate.opsForValue().get("user_" + tokenName);
    }





    /**
     * 按照response获取username
     * 从request中获取Cookie，然后从Cookie中获取到token的userid，从redis中按照userId获取username。
     * @return
     */
    public String getUsernameFromRedisByReq(HttpServletRequest request){
        return this.getUserDetailsByUserId(jwtTokenUtil.getUserIdFromReq(request)).getUsername();
    }











    /**
     * 给redis添加所有用户。
     */
    public void initAllUser() {
        
    }

    /**
     * 让redis删除所有用户。
     */
    public void deletAllUser() {

    }

    /**
     * 更新所有的用户,原则上时删除后再添加。
     */
    public void updateAllUser() {
        this.deletAllUser();
        this.initAllUser();
    }
}