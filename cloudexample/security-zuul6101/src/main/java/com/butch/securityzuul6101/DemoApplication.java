package com.butch.securityzuul6101;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
@EnableEurekaClient
@EnableZuulProxy
/**
 * 扫描工具类的Spring和mybatis组件
 */
@ComponentScan(value = "com.butch.apiutils")
@MapperScan("com.butch.apiutils")
/**
 * 然后继续扫描自己的spring组件
 */
@ComponentScan

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
