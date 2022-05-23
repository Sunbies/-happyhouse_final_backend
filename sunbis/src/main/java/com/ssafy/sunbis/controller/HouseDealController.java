package com.ssafy.sunbis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sunbis.model.dto.HouseInfoDto;
import com.ssafy.sunbis.model.dto.SidoGugunDongDto;
import com.ssafy.sunbis.model.service.HouseDealService;

@RestController
@RequestMapping("/housedeal")
public class HouseDealController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final HouseDealService houseDealService;

	@Autowired
	public HouseDealController(HouseDealService houseDealService) {
		this.houseDealService = houseDealService;
	}
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunDongDto>> sido() throws Exception {
//		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunDongDto>>(houseDealService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunDongDto>> gugun(@RequestParam("sido") String sido) throws Exception {
//		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
		return new ResponseEntity<List<SidoGugunDongDto>>(houseDealService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunDongDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<SidoGugunDongDto>>(houseDealService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@GetMapping("/{dongCode}/{year}/{month}")
	public ResponseEntity<Map<String, Object>> getHouseDealWithMonthInfo(@PathVariable("dongCode") String dongCode,
			@PathVariable("year") String year, @PathVariable("month") String month) {
		System.out.printf("getHouseDealInfo 입력값 = %s, %s년 %s월\n", dongCode, year, month);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<HouseInfoDto> result = houseDealService.getHouseDealWithMonthInfo(dongCode, year, month);
			resultMap.put("infoList", result);
			resultMap.put("message", SUCCESS);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping()
	public ResponseEntity<Map<String, Object>> getHouseDealInfo(@RequestParam("dongCode") String dongCode) {
		System.out.printf("getHouseDealInfo 입력값 = %s\n", dongCode);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<HouseInfoDto> result = houseDealService.getHouseDealInfo(dongCode);
			for(HouseInfoDto dto : result) {
				System.out.println(dto.toString());
			}
			resultMap.put("infoList", result);
			resultMap.put("message", SUCCESS);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
