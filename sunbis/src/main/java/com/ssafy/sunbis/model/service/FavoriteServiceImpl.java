package com.ssafy.sunbis.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sunbis.model.dto.HouseInfoDto;
import com.ssafy.sunbis.model.mapper.FavoriteMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	private final FavoriteMapper favoriteMapper;
	
	@Autowired
	public FavoriteServiceImpl(FavoriteMapper favoriteMapper) {
		this.favoriteMapper = favoriteMapper;
	}

	@Override
	public List<HouseInfoDto> list(String id) throws Exception {
		return favoriteMapper.list(id);
	}

	@Override
	public int count(long aptCode) throws Exception {
		return favoriteMapper.count(aptCode);
	}

	@Override
	public boolean check(Map<String, Object> map) throws Exception {
		return favoriteMapper.check(map) != -1L;
	}

	@Override
	public boolean insert(Map<String, Object> map) throws Exception {
		return favoriteMapper.insert(map) == 1;
	}

	@Override
	public boolean delete(Map<String, Object> map) throws Exception {
		return favoriteMapper.delete(map) == 1;
	}

}
