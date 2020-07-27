package com.butch.securityzuul6101.controller.swagger.test;

import com.butch.apiutils.pojo.User;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "测试类")
@RestController
public class Demo {
    @ApiOperation("方法上的注释")
    @GetMapping("/demo")
    public String getDemo1(@ApiParam(value = "用户参数") User name,@ApiParam("id参数") Integer id){
        return "demo1";
    }

    // @GetMapping("/user")
    // public User getUser(){
    //     return new User("QiuQiuQiu");
    // }
}