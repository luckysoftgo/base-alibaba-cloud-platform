package com.application.cloud.common;

import com.application.cloud.common.redis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : 孤狼
 * @NAME: BasicTest
 * @DESC: BasicTest类设计
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class BasicTest {
	//TODO 测试编写
	
	@Autowired
	private RedisService redisOperClient;
	
	@Test
	public void testRedis() {
		try {
			String key = "aaaa";
			boolean result = redisOperClient.put(key, "bbbbbbbbbbbbbbbbbbbbbbb");
			if (result) {
				Object msg = redisOperClient.get(key);
				System.out.println(msg.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
