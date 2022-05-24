package com.ssafy.sunbis.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.sunbis.model.dto.HouseInfoDto;

public interface FavoriteService {
	List<HouseInfoDto> list(String id) throws Exception;
	int count(long aptCode) throws Exception;
	boolean check(Map<String, Object> map) throws Exception;
	boolean insert(Map<String, Object> map) throws Exception;
	boolean delete(Map<String, Object> map) throws Exception;
}
