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
import com.ssafy.sunbis.model.dto.PageNavDto;
import com.ssafy.sunbis.model.dto.SidoGugunDongDto;
import com.ssafy.sunbis.model.service.HouseDealService;
import com.ssafy.sunbis.model.service.PageNavService;

@RestController
@RequestMapping("/housedeal")
public class HouseDealController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final HouseDealService houseDealService;
	private final PageNavService pageNavService;
	
	@Autowired
	public HouseDealController(HouseDealService houseDealService, PageNavService pageNavService) {
		this.houseDealService = houseDealService;
		this.pageNavService = pageNavService;
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
	public ResponseEntity<Map<String, Object>> getHouseDealInfo(
//			@RequestParam("dongCode") String dongCode) {
			@RequestParam Map<String, String> map) {
		
//		System.out.printf("getHouseDealInfo 입력값 = %s\n", dongCode);
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		try {
//			List<HouseInfoDto> result = houseDealService.getHouseDealInfo(dongCode);
//			for(HouseInfoDto dto : result) {
//				System.out.println(dto.toString());
//			}
//			resultMap.put("infoList", result);
//			resultMap.put("message", SUCCESS);
//		} catch (Exception e) {
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
		logger.debug("getHouseDealInfo 입력값 = {}", map);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message", FAIL);
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		if (map.get("dongCode") == null || map.get("dongCode") == "") {
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String,Object>>(resultMap, status);
		}
		
		try {
			map.put("pg", map.get("pg") == null ? "1" : map.get("pg"));
			map.put("cpp", map.get("cpp") == null ? "10" : map.get("cpp"));
			Map<String, Object> params = new HashMap<>();
			List<HouseInfoDto> infoList = houseDealService.getHouseDealInfo2(map, params);
			int totalCount = houseDealService.getTotalCount(params);
			PageNavDto pageNav = pageNavService.getPageNav(map, totalCount);
			
			if (infoList != null) {
				resultMap.put("infoList", infoList);
				resultMap.put("pageNav", pageNav);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			logger.debug("아파트 목록 조회 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}

}
