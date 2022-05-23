package com.ssafy.sunbis.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.sunbis.model.dto.HouseInfoDto;
import com.ssafy.sunbis.model.dto.SidoGugunDongDto;

public interface HouseDealService {
	List<HouseInfoDto> getHouseDealWithMonthInfo(String dongCode, String year, String month);

	List<HouseInfoDto> getHouseDealInfo(String dongCode);

	List<HouseInfoDto> getHouseDealInfo2(Map<String, String> map, Map<String, Object> params);
	
	int getTotalCount(Map<String, Object> params);

	List<SidoGugunDongDto> getSido();

	List<SidoGugunDongDto> getGugunInSido(String sido);

	List<SidoGugunDongDto> getDongInGugun(String gugun);
}