package com.butch.apiutils;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
/**
 *扫描入口
 */
//当前包下所有spring组件
@ComponentScan
//当前包下所有Mybatis文件
@MapperScan
public class ApiScan {
    
}