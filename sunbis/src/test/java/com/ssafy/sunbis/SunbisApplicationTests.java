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
	HouseDealMapper m;

	@Test
	void mapperTest() {
		assertNotNull(m);
		Map<String, Object> params = new HashMap<>();
		params.put("dongCode", "1111017400");
		params.put("start", 0);
		params.put("cpp", 10);
		params.put("apartmentName", "");
		params.put("orderby", "dealDate");
		params.put("order", "asc");
		System.out.println(m.getHouseDealInfo2(params));
	}

}


















