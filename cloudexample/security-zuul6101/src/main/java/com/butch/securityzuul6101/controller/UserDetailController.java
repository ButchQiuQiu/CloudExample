package com.butch.securityzuul6101.controller;

import javax.servlet.http.HttpServletRequest;

import com.butch.apiutils.redis.RedisUserUtil;
import com.butch.securityzuul6101.service.UserDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理用户登录和验证权限的Controller
 * 
 * 
 */
@RestController()
@RequestMapping("/user")
public class UserDetailController {
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisUserUtil redisUserUtil;
    /**
     * 前端需要的信息:姓名和部门
     * @param authentication
     * @return 当前用户登录的信息
     * @throws JsonProcessingException json转换失败
     */
    @PostMapping("/status")
    public String getUserStatus() throws JsonProcessingException {
        String myUsername = redisUserUtil.getUsernameFromRedisByReq(request);
        return userDetailService.getUserDeprtAndNameJson(myUsername);
    }
}