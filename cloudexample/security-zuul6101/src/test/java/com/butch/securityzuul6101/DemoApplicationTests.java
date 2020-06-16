package com.butch.securityzuul6101;

import com.butch.apiutils.jwt.JwtProperties;
import com.butch.apiutils.mapper.UserMapper;
import com.butch.apiutils.pojo.UserTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	JwtProperties jwtProperties;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Test
	void demo22() {
		System.out.println("输出中-----------------------------------------");
		System.out.println(jwtProperties.toString());
		// System.out.println(securityConfig.toString());
	}

	@Test
	void demoRedis() throws JsonProcessingException {
		UserTest user = new UserTest("111", "222");
		// 使用json
		redisTemplate.opsForValue().set("user1", new ObjectMapper().writeValueAsString(user));
		Object object1 = redisTemplate.opsForValue().get("user1");
		System.out.println("json:" + object1);
		// 使用序列化
		redisTemplate.opsForValue().set("user2", user);
		Object object2 = redisTemplate.opsForValue().get("user2");
		System.out.println("序列化" + object2.toString());
		// 此时redis中存储的是String类型会强转失败
		// System.out.println("强转user1,显视json序列化:"+(User) object1);
		// Redis存入中会被强转成json取出时会被JackJson强转成相应的对象.
		System.out.println("强转user2,隐式json序列化:" + (UserTest) object2);
	}

	@Autowired
	UserMapper userMapper;
	@Test
	void demoRedisPojoUtil(){
		System.out.println(userMapper.getUserByUsername("Qiu123456"));
	}
}
