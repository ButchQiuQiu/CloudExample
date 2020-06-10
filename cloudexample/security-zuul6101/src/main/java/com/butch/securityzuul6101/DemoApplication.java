package com.butch.securityzuul6101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
//扫描jwt工具类
@ComponentScan({"com.butch.apiutils.jwt"})
@EnableWebSecurity
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
