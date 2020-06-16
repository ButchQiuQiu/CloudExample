package com.butch.securityzuul6101.security.handle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.butch.apiutils.jwt.JwtTokenUtil;
import com.butch.apiutils.jwt.JwtUtil;
import com.butch.apiutils.redis.RedisUserUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * MyLogoutSuccessHandler 注销成功自定义处理器
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private RedisUserUtil redisUserUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        redisUserUtil.delUserDetailsByUserId(jwtTokenUtil.getUserIdFromReq(request));
        System.out.println("注销成功");
    }

}