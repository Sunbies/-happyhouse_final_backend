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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.sunbis.model.dto.PageNavDto;
import com.ssafy.sunbis.model.dto.PostDto;
import com.ssafy.sunbis.model.service.PageNavService;
import com.ssafy.sunbis.model.service.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@Controller
public class PostController {
	
	public static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final PostService postService;
	private final PageNavService pageNavService;
	
	@Autowired
	public PostController(PostService postService, PageNavService pageNavService) {
		this.postService = postService;
		this.pageNavService = pageNavService;
	}
	
	@ApiOperation(value = "글 목록", response = Map.class)
	@GetMapping("/board/{boardtype}")
	public ResponseEntity<?> list(
			@ApiParam("\"notice\", \"qna\", \"realestate\"")
			@PathVariable("boardtype") int boardtype
			, @ApiParam("{ pg, cpp, key, word }")
			@RequestParam(required = false) Map<String, String> map) {
		
		logger.debug("글 목록: {}, {}", boardtype, map);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);

		map.put("table", "post");
		map.put("pk", "postno");
		if (boardtype < 0 || boardtype > 2) {
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
		map.put("boardtype", Integer.toString(boardtype));
		
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
			List<PostDto> postList = postService.list(map);
			PageNavDto pageNav = pageNavService.getPageNav(map);
			status = HttpStatus.OK;
			
			if (postList != null) {
				resultMap.put("message", SUCCESS);
				resultMap.put("postList", postList);
				resultMap.put("pageNav", pageNav);
			}
		} catch (Exception e) {
			logger.debug("글 목록 조회 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글 조회", response = Map.class)
	@GetMapping("/post/{postno}")
	public ResponseEntity<?> select(
			@ApiParam("postno")
			@PathVariable("postno") int postno) {
		
		logger.debug("게시글 조회: {}", postno);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			PostDto post = postService.select(postno);
			status = HttpStatus.OK;
			
			if (post != null) {
				resultMap.put("message", SUCCESS);
				resultMap.put("post", post);
			}
		} catch (Exception e) {
			logger.debug("게시글 조회 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글 등록", response = Map.class)
	@PostMapping("/post")
	public ResponseEntity<?> insert(
			@ApiParam("{ boardtype, writer, title, content}")
			@RequestBody Map<String, String> map) {
		
		logger.debug("게시글 등록: {}", map);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			PostDto post = new PostDto();
			post.setBoardtype(Integer.parseInt(map.get("boardtype")));
			post.setWriter(map.get("writer"));
			post.setTitle(map.get("title"));
			post.setContent(map.get("content"));
			
			if (postService.insert(post)) {
				resultMap.put("message", SUCCESS);
			}
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			logger.debug("게시글 등록 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글 수정", response = Map.class)
	@PutMapping("/post")
	public ResponseEntity<?> update(
			@ApiParam("{ postno, title, content}")
			@RequestBody Map<String, String> map) {
		
		logger.debug("게시글 수정: {}", map);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			PostDto post = new PostDto();
			post.setPostno(Integer.parseInt(map.get("postno")));
			post.setTitle(map.get("title"));
			post.setContent(map.get("content"));
			
			if (postService.update(post)) {
				resultMap.put("message", SUCCESS);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.debug("게시글 수정 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글 삭제", response = Map.class)
	@DeleteMapping("/post/{postno}")
	public ResponseEntity<?> delete(
			@ApiParam("{ postno, title, content}")
			@PathVariable("postno") int postno) {
		
		logger.debug("게시글 삭제: {}", postno);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			if (postService.delete(postno)) {
				resultMap.put("message", SUCCESS);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.debug("게시글 삭제 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
