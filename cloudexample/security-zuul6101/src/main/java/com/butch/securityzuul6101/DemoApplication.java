package com.butch.securityzuul6101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
@EnableEurekaClient
@EnableZuulProxy
//扫描jwt工具类
@ComponentScan(value = "com.butch.apiutils.jwt")
//然后继续默认扫描
@ComponentScan
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
