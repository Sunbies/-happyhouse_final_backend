package com.ssafy.sunbis.model.service;

import java.util.List;

import com.ssafy.sunbis.model.dto.HouseInfoDto;
import com.ssafy.sunbis.model.dto.SidoGugunDongDto;

public interface HouseDealService {
	List<HouseInfoDto> getHouseDealWithMonthInfo(String dongCode, String year, String month);

	List<HouseInfoDto> getHouseDealInfo(String dongCode);

	List<SidoGugunDongDto> getSido();

	List<SidoGugunDongDto> getGugunInSido(String sido);

	List<SidoGugunDongDto> getDongInGugun(String gugun);
}