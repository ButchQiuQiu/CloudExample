package com.butch.datatable8002.controller;

import javax.servlet.http.HttpServletRequest;

import com.butch.apiutils.pojo.User;
import com.butch.apiutils.redis.RedisUserUtil;
import com.butch.datatable8002.service.DataTableService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * datatable的CURD操作
 */
@RestController
@RequestMapping("/user")
public class DataTableController {
    @Autowired
    private DataTableService dataTableService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisUserUtil redisUserUtil;

    /**
     * 获取用户能查阅的角色
     * 
     * @return json字符串:处理的结果
     * @throws JsonProcessingException
     */
    @GetMapping()
    public String getAllUserAndFkByUserRole() throws JsonProcessingException {
        String myUsername = redisUserUtil.getUsernameFromRedisByReq(request);
        return dataTableService.getAllUserAndFkByUserRole(myUsername);
    }

    /**
     * 删除角色
     * 
     * @param username 需要操作的用户名
     * @return json字符串:处理的结果
     * @throws JsonProcessingException
     */
    @DeleteMapping()
    public String deleteUserByUsername(String username) throws JsonProcessingException {
        String myUsername = redisUserUtil.getUsernameFromRedisByReq(request);
        return dataTableService.deleteUserByUsername(myUsername, username);
    }

    /**
     * 修改角色
     * 
     * @param username 目标用户username
     * @param column   需要修改的字段
     * @param value    需要修改的值
     * @return json字符串:处理的结果
     * @throws JsonProcessingException
     */
    @PatchMapping()
    public String updateUserByUserMap(String username, String column, String value) throws JsonProcessingException {
        String myUsername = redisUserUtil.getUsernameFromRedisByReq(request);
        return dataTableService.updateUserByUserMap(myUsername, username, column,
                value);
    }

    /**
     * 添加以为用户
     * 
     * @param targetUser 添加的用户
     * @return json字符串:处理的结果
     * @throws JsonProcessingException
     */
    @PostMapping()
    public String createUserByUser(@RequestBody User user) throws JsonProcessingException {
        String myUsername=redisUserUtil.getUsernameFromRedisByReq(request);
        return dataTableService.insertUserByUserMap(myUsername, user);
    }
}