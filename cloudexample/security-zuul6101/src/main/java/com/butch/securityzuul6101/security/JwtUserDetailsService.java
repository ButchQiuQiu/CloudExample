package com.butch.securityzuul6101.security;

import com.butch.apiutils.mapper.UserMapper;
import com.butch.apiutils.pojo.User;
import com.butch.securityzuul6101.pojo.UserExDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper mapper;
    /**
     * user是context中的username,从username中获取用户的权限信息。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("数据库中没有此用户");
        }
        return new UserExDetails(user);
    }

}