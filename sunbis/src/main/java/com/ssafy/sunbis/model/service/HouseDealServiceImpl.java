package com.ssafy.sunbis.model.service;

import java.util.List;

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