package com.ssafy.sunbis.model.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sunbis.model.dto.HouseInfoDto;
import com.ssafy.sunbis.model.dto.SidoGugunDongDto;
import com.ssafy.sunbis.model.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	private final HouseDealMapper houseDealMapper;

	@Autowired
	public HouseDealServiceImpl(HouseDealMapper houseDealMapper) {
		this.houseDealMapper = houseDealMapper;
	}

	@Override
	public List<HouseInfoDto> getHouseDealWithMonthInfo(String dongCode, String year, String month) {
		return houseDealMapper.getHouseDealWithMonthInfo(dongCode, year, month);
	}

	@Override
	public List<HouseInfoDto> getHouseDealInfo(String dongCode) {
		return houseDealMapper.getHouseDealInfo(dongCode);
	}

	@Override
	public List<HouseInfoDto> getHouseDealInfo2(Map<String, String> map, Map<String, Object> params) {
		params.putAll(map);
		
		String orderby = map.get("orderby");
		params.put("orderby", orderby == null ? "no" : orderby);
		params.put("order", "asc".equals(map.get("order")) ? "asc" : "desc");
		
		String apartmentName = map.get("aptname");
		params.put("apartmentName", apartmentName == null ? "" : apartmentName);
		
		int currentPageNo = Integer.parseInt(map.get("pg"));
		int countPerPage = Integer.parseInt(map.get("cpp"));
		int start = (currentPageNo - 1) * countPerPage;
		
		params.put("start", start);
		params.put("cpp", countPerPage);
		
		return houseDealMapper.getHouseDealInfo2(params);
	}

	@Override
	public List<HouseInfoDto> getHouseDealInfoLatest4() {
		return houseDealMapper.getHouseDealInfoLatest4();
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		return houseDealMapper.getTotalCount(params);
	}

	@Override
	public List<SidoGugunDongDto> getSido() {
		// TODO Auto-generated method stub
		return houseDealMapper.getSido();
	}

	@Override
	public List<SidoGugunDongDto> getGugunInSido(String sido) {
		// TODO Auto-generated method stub
		return houseDealMapper.getGugunInSido(sido);
	}

	@Override
	public List<SidoGugunDongDto> getDongInGugun(String gugun) {
		// TODO Auto-generated method stub
		return houseDealMapper.getDongInGugun(gugun);
	}

}