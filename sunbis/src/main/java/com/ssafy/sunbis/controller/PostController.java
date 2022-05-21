package com.ssafy.sunbis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.sunbis.model.dto.PageNavDto;
import com.ssafy.sunbis.model.dto.PostDto;
import com.ssafy.sunbis.model.service.PageNavService;
import com.ssafy.sunbis.model.service.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@Controller
@RequestMapping("/post")
public class PostController {
	
	public static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private static Map<String, String> boardTypeMap;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	static {
		boardTypeMap = new HashMap<>();
		boardTypeMap.put("notice", "0");
		boardTypeMap.put("qna", "1");
		boardTypeMap.put("realestate", "2");
	}
	
	private final PostService postService;
	private final PageNavService pageNavService;
	
	@Autowired
	public PostController(PostService postService, PageNavService pageNavService) {
		this.postService = postService;
		this.pageNavService = pageNavService;
	}
	
	@ApiOperation(value = "글 목록", notes = "", response = Map.class)
	@GetMapping("/{boardname}")
	public ResponseEntity<?> list(@PathVariable("boardname") String boardname
			, @RequestParam Map<String, String> map) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		map.put("table", "post");
		map.put("pk", "postno");
		map.put("boardtype", boardTypeMap.get(boardname));
		
		String word = map.get("word");
		String key = map.get("key");
		if (word == null || "".equals(word) || key == null || "".equals(key)) {
			map.put("key", "");
			map.put("word", "");
		} else {
			map.put("key", key);
			map.put("word", word);
		}
		
		String pg = map.get("pg");
		map.put("pg", pg == null ? "1" : pg);
		String cpp = map.get("cpp");
		map.put("cpp", cpp == null ? "10" : cpp);
		
		try {
			
			List<PostDto> list = postService.list(map);
			PageNavDto pageNav = pageNavService.getPageNav(map);
			status = HttpStatus.ACCEPTED;
			
			if (list != null) {
				resultMap.put("list", list);
				resultMap.put("pageNav", pageNav);
				resultMap.put("message", SUCCESS);
			} else {
				resultMap.put("message", FAIL);
			}
			
		} catch (Exception e) {
			logger.debug("글 목록 얻기 중 오류 {}", e.getMessage());
			resultMap.put("message", FAIL);
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}

















