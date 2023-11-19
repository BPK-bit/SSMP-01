package com.zust;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zust.domain.Entities.Book;
import com.zust.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class Ssmp01ApplicationTests {
	@Autowired
	RedisTemplate<String,Object> redisTemplate;

	@Test
	void contextLoads() throws InterruptedException {
		String key ="getByPage_";
		redisTemplate.opsForValue().set(key,"aaaaa",2, TimeUnit.SECONDS);

		if (redisTemplate.hasKey(key)){
			String page = (String) redisTemplate.opsForValue().get(key);
			System.out.println(page);
		}

	}

}
