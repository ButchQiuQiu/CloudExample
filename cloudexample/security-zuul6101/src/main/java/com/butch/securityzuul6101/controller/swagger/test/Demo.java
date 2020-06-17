package com.butch.securityzuul6101.controller.swagger.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("这是个在Controller的注释")
@RestController
public class Demo {
    @ApiOperation("方法上的注释")
    @GetMapping("/demo")
    public String getDemo1(){
        return "demo1";
    }

    // @GetMapping("/user")
    // public User getUser(){
    //     return new User("QiuQiuQiu");
    // }
}