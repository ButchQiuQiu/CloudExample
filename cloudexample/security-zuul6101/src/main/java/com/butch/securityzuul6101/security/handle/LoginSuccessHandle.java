package com.butch.securityzuul6101.security.handle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.butch.apiutils.jwt.JwtProperties;
import com.butch.apiutils.jwt.JwtTokenUtil;
import com.butch.apiutils.pojo.User;
import com.butch.apiutils.redis.RedisUserUtil;
import com.butch.securityzuul6101.pojo.UserExDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * LoginSuccessHandle 
 * 登录验证成功后的处理
 */
@Component
public class LoginSuccessHandle implements AuthenticationSuccessHandler {
    @Autowired
    private RedisUserUtil redisUserUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // 登录成功后的处理
        PrintWriter writer = response.getWriter();
        //往redis中丢user数据
        User user=((UserExDetails)authentication.getPrincipal()).getUser();
        redisUserUtil.setRedisUserByUser(user);   
        //制作JWT并放入header
        String token = jwtTokenUtil.generateToken(user);
        response.setHeader(jwtProperties.getToken(), token);
        System.out.println("token为："+token+"\n 成品token："+response.getHeader(jwtProperties.getToken()));
        // 添加jackson
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        map.put("returnIsTrue", "true");
        writer.write(mapper.writeValueAsString(map));
        writer.flush();
        writer.close();
    }
}