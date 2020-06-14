package com.butch.apiutils.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.butch.apiutils.redis.pojo.RedisUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Redis的工具类，
 */
@Component
public class RedisUserUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 按照UserDetail存储登录缓存,key为user_username,value目前只有RedisUserDetails的权限.
     * 这里不能强制转换，只能一个一个翻译转换否则会json序列化失败。因为SimpleGrantedAuthority没有空构造器。
     * 存在时间为yaml中设置
     */
    @SuppressWarnings("all")
    public void setRedisUserByUserDetails(UserDetails userDetails) {
        // List<String> authorities =new ArrayList<String>();
        // Iterator<? extends GrantedAuthority> iterator = userDetails.getAuthorities().iterator();
        // while(iterator.hasNext()){
        //     authorities.add(iterator.next().toString());
        // }
        redisTemplate.opsForValue().set("user_" + userDetails.getUsername(),
                new RedisUserDetails(userDetails.getUsername(),userDetails.getAuthorities()), redisProperties.getUser_expiration(),
                TimeUnit.SECONDS);
    }

    /**
     * 按照user_username获取到对应的RedisUserDetails
     */
    public RedisUserDetails getUserDetailsByUsername(String username) {
        return (RedisUserDetails) redisTemplate.opsForValue().get("user_" + username);
    }

    /**
     * 把Collection<? extends GrantedAuthority>转换成List<String>,因为默认的权限类没有空构造器不能json序列化.
     */
    public List<String> CollecGrantedToListStr(Collection<? extends GrantedAuthority> collecGranted){
        List<String> authorities =new ArrayList<String>();
        Iterator<? extends GrantedAuthority> iterator = collecGranted.iterator();
        while(iterator.hasNext()){
            authorities.add(iterator.next().toString());
        }
        return authorities;
    }

    /**
     * 把List<String>转换成Collection<? extends GrantedAuthority>
     */
    public Collection<? extends GrantedAuthority> ListStrToCollecGranted(List<String> listStr){
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for(String str:listStr){
            list.add(new SimpleGrantedAuthority(str));
        }
        return list;
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