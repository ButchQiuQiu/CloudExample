package com.butch.datatable8002.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 * 测试用
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "测试";
    }
    @GetMapping("/ceshi")
    public String ceshi(){
        return "测试";
    }
}