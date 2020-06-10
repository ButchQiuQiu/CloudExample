package com.butch.securityzuul6101;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.butch.apiutils.jwt.JwtServerProperties;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	JwtServerProperties jwtServerProperties;
	@Test
	void contextLoads() {
		
	}

	@Test
	void demo22(){
		System.out.println("输出中-----------------------------------------");
		System.out.println(jwtServerProperties.toString());
		// System.out.println(securityConfig.toString());
	}

}
