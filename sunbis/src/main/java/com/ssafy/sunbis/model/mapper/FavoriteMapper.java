package com.ssafy.sunbis.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.sunbis.model.dto.HouseInfoDto;

@Repository
@Mapper
public interface FavoriteMapper {
	List<HouseInfoDto> list(@Param("id") String id);
	int count(@Param("aptCode") long aptCode);
	long check(Map<String, Object> map);
	int insert(Map<String, Object> map);
	int delete(Map<String, Object> map);
}
