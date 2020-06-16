package com.butch.securityzuul6101.security.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.butch.apiutils.jwt.JwtTokenUtil;
import com.butch.apiutils.redis.RedisUserUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * MyLogoutSuccessHandler 注销成功自定义处理器
 */
@Slf4j
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
            log.info("注销成功");
    }

}