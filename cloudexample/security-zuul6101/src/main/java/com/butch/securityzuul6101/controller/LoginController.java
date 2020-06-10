package com.butch.securityzuul6101.controller;

import javax.servlet.http.HttpServletRequest;

import com.butch.apiutils.jwt.JwtTokenUtil;
import com.butch.apiutils.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/login")
    public String login(@RequestBody User sysUser, HttpServletRequest request){
        System.out.println("接入成功");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(sysUser.getUsername());
        final String token = jwtTokenUtil.generateToken((User)userDetails);
        return token;
    }

    @GetMapping("/haha")
    public String haha(){
        System.out.println("上下文是否还保存："+org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication());
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "haha:"+userDetails.toString();
    }
}