package com.ssafy.sunbis.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PageNavMapper {
	/**
	 * {
	 *   table: 테이블명,
	 *   pk: 프라이머리 키 칼럼명,
	 *   key: 칼럼명 (where절에 들어감, title의 경우 like 적용),
	 *   word: 값 (Null 또는 ""일 경우 where절 없음)
	 * }
	 */
	int getTotalCount(Map<String, String> map);
}
