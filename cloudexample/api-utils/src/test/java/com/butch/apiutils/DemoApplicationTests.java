package com.butch.apiutils;

import com.butch.apiutils.jwt.JwtServerProperties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	JwtServerProperties jwt;
	@Test
	void contextLoads() {
		System.out.println(jwt.getSecret());
	}

}
