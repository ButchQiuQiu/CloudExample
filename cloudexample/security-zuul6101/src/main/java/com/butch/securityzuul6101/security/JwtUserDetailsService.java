package com.butch.securityzuul6101.security;

import java.util.ArrayList;
import java.util.List;

import com.butch.securityzuul6101.pojo.UserAuth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    /**
     * user是context中的username,从username中获取用户的权限信息。
     */
    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        System.out.println("JwtUserDetailsService:" + user);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        return new UserAuth(user,"qiuqiu123",authorityList);
    }

}