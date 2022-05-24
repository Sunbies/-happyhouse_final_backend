package com.ssafy.sunbis;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.sunbis.model.dto.*;
import com.ssafy.sunbis.model.mapper.*;

@SpringBootTest
class SunbisApplicationTests {
	
	@Autowired
	FavoriteMapper m;

	@Test
	void mapperTest() {
		assertNotNull(m);
		System.out.println(m.list("ssafy"));
	}

}


















