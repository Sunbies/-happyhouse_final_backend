package com.ssafy.sunbis;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.sunbis.model.mapper.PostMapper;

@SpringBootTest
class SunbisApplicationTests {
	
	@Autowired
	PostMapper p;

	@Test
	void mapperTest() {
		assertNotNull(p);
		System.out.println(p.select(5));
	}

}


















