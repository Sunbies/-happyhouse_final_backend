package com.ssafy.sunbis.controller;

import java.util.HashMap;
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

import com.ssafy.sunbis.exception.PostLikeException;
import com.ssafy.sunbis.model.service.PostLikeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@Controller
public class PostLikeController {
	
	public static final Logger logger = LoggerFactory.getLogger(PostLikeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private PostLikeService postLikeService;
	
	@Autowired
	public PostLikeController(PostLikeService postLikeService) {
		this.postLikeService = postLikeService;
	}
	
	@ApiOperation(value = "게시글의 좋아요 싫어요 카운트 조회", response = Map.class)
	@GetMapping("/post/{postno}/likecounts")
	public ResponseEntity<?> likeCounts(
			@ApiParam("postno")
			@PathVariable("postno") int postno) {
		
		logger.debug("게시글의 좋아요 싫어요 카운트 조회: {}", postno);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			Map<String, Integer> likeCounts = postLikeService.likeCounts(postno);
			
			if (likeCounts != null) {
				status = HttpStatus.OK;
				resultMap.put("message", SUCCESS);
				resultMap.put("likeCounts", likeCounts);
			}
		} catch (Exception e) {
			logger.debug("좋아요 싫어요 카운트 조회 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글에 대한 사용자의 좋아요 싫어요 포지션", response = Map.class)
	@GetMapping("/post/{postno}/{id}/position")
	public ResponseEntity<?> select(
			@ApiParam("postno")
			@PathVariable("postno") int postno
			, @ApiParam("id")
			@PathVariable("id") String id) {
		
		logger.debug("게시글 사용자 포지션: postno={}, id={}", postno, id);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		Map<String, Object> map = new HashMap<>();
		map.put("postno", postno);
		map.put("id", id);
		
		try {
			int position = postLikeService.selectLike(map);
			
			status = HttpStatus.OK;
			resultMap.put("message", SUCCESS);
			resultMap.put("position", position);
		} catch (Exception e) {
			logger.debug("게시글 사용자 포지션 조회 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글에 좋아요 싫어요 누르기", response = Map.class)
	@PostMapping("/post/{postno}/{id}/{position}")
	public ResponseEntity<?> insert(
			@ApiParam("postno")
			@PathVariable("postno") int postno
			, @ApiParam("id")
			@PathVariable("id") String id
			, @ApiParam("\"like\", \"dislike\"")
			@PathVariable("position") String position) {
		
		logger.debug("게시글 사용자 포지션 인서트: postno={}, id={}, position={}");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		Map<String, Object> map = new HashMap<>();
		map.put("postno", postno);
		map.put("id", id);
		if (!"like".equals(position) && !"dislike".equals(position)) {
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
		map.put("position", "like".equals(position) ? 1 : -1);
		
		try {
			if (postLikeService.insertLike(map)) {
				status = HttpStatus.CREATED;
				resultMap.put("message", SUCCESS);
			}
		} catch (PostLikeException ple) {
			status = HttpStatus.CONFLICT;
			logger.debug("게시글 사용자 포지션 인서트 중 포지션 오류");
		} catch (Exception e) {
			logger.debug("게시글 사용자 포지션 인서트 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글에 좋아요 싫어요 변경", response = Map.class)
	@PutMapping("/post/{postno}/{id}/{position}")
	public ResponseEntity<?> update(
			@ApiParam("postno")
			@PathVariable("postno") int postno
			, @ApiParam("id")
			@PathVariable("id") String id
			, @ApiParam("\"like\", \"dislike\"")
			@PathVariable("position") String position) {
		
		logger.debug("게시글 사용자 포지션 업데이트: postno={}, id={}, position={}");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		Map<String, Object> map = new HashMap<>();
		map.put("postno", postno);
		map.put("id", id);
		if (!"like".equals(position) && !"dislike".equals(position)) {
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
		map.put("position", "like".equals(position) ? 1 : -1);
		
		try {
			if (postLikeService.updateLike(map)) {
				status = HttpStatus.ACCEPTED;
				resultMap.put("message", SUCCESS);
			}
		} catch (PostLikeException ple) {
			status = HttpStatus.NOT_ACCEPTABLE;
			logger.debug("게시글 사용자 포지션 업데이트 중 포지션 오류");
		} catch (Exception e) {
			logger.debug("게시글 사용자 포지션 업데이트 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "게시글에 좋아요 싫어요 삭제", response = Map.class)
	@DeleteMapping("/post/{postno}/{id}/position")
	public ResponseEntity<?> delete(
			@ApiParam("postno")
			@PathVariable("postno") int postno
			, @ApiParam("id")
			@PathVariable("id") String id) {
		
		logger.debug("게시글 사용자 포지션 삭제: postno={}, id={}");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		Map<String, Object> map = new HashMap<>();
		map.put("postno", postno);
		map.put("id", id);
		
		try {
			if (postLikeService.deleteLike(map)) {
				status = HttpStatus.ACCEPTED;
				resultMap.put("message", SUCCESS);
			}
		} catch (PostLikeException ple) {
			status = HttpStatus.NOT_FOUND;
			logger.debug("게시글 사용자 포지션 삭제 중 포지션 오류");
		} catch (Exception e) {
			logger.debug("게시글 사용자 포지션 삭제 중 오류: {}", e.getMessage());
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
