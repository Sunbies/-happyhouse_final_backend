package com.ssafy.sunbis;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.sunbis.model.mapper.MemberMapper;

@SpringBootTest
class SunbisApplicationTests {
	
	@Autowired
	MemberMapper mMapper;

	@Test
	void mapperTest() {
		assertNotNull(mMapper);
		System.out.println(mMapper.selectAll());
	}

}
