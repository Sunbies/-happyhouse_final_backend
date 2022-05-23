package com.ssafy.sunbis.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.sunbis.model.dto.HouseInfoDto;
import com.ssafy.sunbis.model.dto.SidoGugunDongDto;

@Repository
@Mapper
public interface HouseDealMapper {
	List<HouseInfoDto> getHouseDealWithMonthInfo(String dongCode, String year, String month);

	List<HouseInfoDto> getHouseDealInfo(String dongCode);

	List<HouseInfoDto> getHouseDealInfo2(Map<String, Object> map);
	
	int getTotalCount(Map<String, Object> map);

	List<SidoGugunDongDto> getSido();

	List<SidoGugunDongDto> getGugunInSido(String sido);

	List<SidoGugunDongDto> getDongInGugun(String gugun);
}