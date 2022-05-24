package com.ssafy.sunbis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sunbis.model.dto.HouseInfoDto;
import com.ssafy.sunbis.model.service.FavoriteService;

@RestController
public class FavoriteController {
	
	public static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final FavoriteService favoriteService;
	
	@Autowired
	public FavoriteController(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
	}
	
	@GetMapping("/member/{id}/house/favorites")
	public ResponseEntity<?> list(@PathVariable("id") String id) {
		logger.debug("list 회원의 즐겨찾기 목록 조회: {}", id);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			List<HouseInfoDto> favoriteList = favoriteService.list(id);
			if (favoriteList != null) {
				resultMap.put("favoriteList", favoriteList);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			logger.debug("list 회원의 즐겨찾기 목록 조회 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/house/{aptCode}/fcount")
	public ResponseEntity<?> count(@PathVariable("aptCode") long aptCode) {
		logger.debug("count 아파트 즐겨찾는 인원수 조회: {}", aptCode);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			int count = favoriteService.count(aptCode);
			resultMap.put("favoriteCount", count);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.debug("count 아파트 즐겨찾는 인원수 조회 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/member/{id}/house/{aptCode}/position")
	public ResponseEntity<?> check(
			@PathVariable("id") String id
			, @PathVariable("aptCode") long aptCode) {
		logger.debug("check 회원의 아파트에 대한 즐겨찾기 여부 조회: {}, {}", id, aptCode);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("aptCode", aptCode);
			resultMap.put("isFavorite", favoriteService.check(map));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.debug("check 회원의 아파트에 대한 즐겨찾기 여부 조회 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/member/{id}/house/{aptCode}")
	public ResponseEntity<?> insert(
			@PathVariable("id") String id
			, @PathVariable("aptCode") long aptCode) {
		logger.debug("insert 즐겨찾기 처리: {}, {}", id, aptCode);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("aptCode", aptCode);
			if (favoriteService.insert(map)) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.CREATED;
			}
		} catch (Exception e) {
			logger.debug("insert 즐겨찾기 처리 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@DeleteMapping("/member/{id}/house/{aptCode}")
	public ResponseEntity<?> delete(
			@PathVariable("id") String id
			, @PathVariable("aptCode") long aptCode) {
		logger.debug("delete 즐겨찾기 철회: {}, {}", id, aptCode);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("aptCode", aptCode);
			if (favoriteService.delete(map)) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.debug("delete 즐겨찾기 철회 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
