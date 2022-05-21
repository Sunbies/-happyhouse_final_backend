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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.sunbis.model.dto.ReplyDto;
import com.ssafy.sunbis.model.service.ReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	public static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final ReplyService replyService;
	
	@Autowired
	public ReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@ApiOperation(value = "댓글 달기", response = Map.class)
	@PostMapping
	public ResponseEntity<?> insert(
			@ApiParam("{ postno, writer, content }")
			@RequestBody ReplyDto reply) {
		
		logger.debug("댓글 달기: {}", reply);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			if (replyService.insert(reply)) {
				status = HttpStatus.CREATED;
				resultMap.put("message", SUCCESS);
			}
		} catch (Exception e) {
			logger.debug("댓글 달기 중 오류: {}", e.getMessage());
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "댓글 수정", response = Map.class)
	@PutMapping
	public ResponseEntity<?> update(
			@ApiParam("{ replyno, content }")
			@RequestBody ReplyDto reply) {
		
		logger.debug("댓글 수정: {}", reply);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			if (replyService.update(reply)) {
				status = HttpStatus.ACCEPTED;
				resultMap.put("message", SUCCESS);
			}
		} catch (Exception e) {
			logger.debug("댓글 수정 중 오류: {}", e.getMessage());
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "댓글 수정", response = Map.class)
	@DeleteMapping("/{replyno}")
	public ResponseEntity<?> delete(
			@ApiParam("replyno")
			@PathVariable("replyno") int replyno) {
		
		logger.debug("댓글 삭제: {}", replyno);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		resultMap.put("message", FAIL);
		
		try {
			if (replyService.delete(replyno)) {
				status = HttpStatus.ACCEPTED;
				resultMap.put("message", SUCCESS);
			}
		} catch (Exception e) {
			logger.debug("댓글 삭제 중 오류: {}", e.getMessage());
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
