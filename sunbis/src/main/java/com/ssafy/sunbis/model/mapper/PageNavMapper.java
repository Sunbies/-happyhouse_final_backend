package com.ssafy.sunbis.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PageNavMapper {
	
	/**
	 * table: 테이블명,
	 * pk: 프라이머리 키 칼럼명,
	 * key: 칼럼명,
	 * word: 값,
	 * 
	 * boardtype: table이 "post"인 경우 적용되는 boardtype
	 * 
	 * @param map
	 * @return total count
	 */
	int getTotalCount(Map<String, String> map);
}
