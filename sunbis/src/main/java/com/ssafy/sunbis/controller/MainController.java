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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sunbis.model.dto.HouseInfoDto;
import com.ssafy.sunbis.model.dto.PostDto;
import com.ssafy.sunbis.model.service.HouseDealService;
import com.ssafy.sunbis.model.service.PostService;

@RestController
@RequestMapping("/main")
public class MainController {
	
	public static final Logger logger = LoggerFactory.getLogger(MainController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final PostService postService;
	private final HouseDealService houseDealService;
	
	@Autowired
	public MainController(PostService postService, HouseDealService houseDealService) {
		this.postService = postService;
		this.houseDealService = houseDealService;
	}
	
	@GetMapping("/latestdeals")
	public ResponseEntity<?> mainLatestDeals() {
		logger.debug("mainLatestDeals 메인을 위한 최근 실거래가");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			List<HouseInfoDto> infoList = houseDealService.getHouseDealInfoLatest4();
			if (infoList != null) {
				resultMap.put("infoList", infoList);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			logger.debug("mainLatestDeals 메인을 위한 최근 실거래가 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/latestposts")
	public ResponseEntity<?> mainLatestPosts() {
		logger.debug("mainLatestPosts 메인을 위한 최근 게시글");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			List<PostDto> postList = postService.listLatestPosts();
			if (postList != null) {
				resultMap.put("postList", postList);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			logger.debug("mainLatestPosts 메인을 위한 최근 게시글 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/hotposts")
	public ResponseEntity<?> mainHotPosts() {
		logger.debug("mainHotPosts 메인을 위한 인기 게시글");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			List<PostDto> postList = postService.listHotPosts();
			if (postList != null) {
				resultMap.put("postList", postList);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			logger.debug("mainHotPosts 메인을 위한 인기 게시글 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}

















