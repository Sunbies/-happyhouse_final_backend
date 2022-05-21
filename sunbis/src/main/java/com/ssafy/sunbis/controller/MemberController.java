package com.ssafy.sunbis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.sunbis.model.dto.MemberDto;
import com.ssafy.sunbis.model.service.JwtService;
import com.ssafy.sunbis.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@Controller
@RequestMapping("/member")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final MemberService memberService;
	private final JwtService jwtService;

	@Autowired
	public MemberController(MemberService memberService, JwtService jwtService) {
		this.memberService = memberService;
		this.jwtService = jwtService;
	}

	@ApiOperation(value = "유저 체크", notes = "id와 password를 받아서 그런 유저가 있는지 확인", response = Map.class)
	@PostMapping("/check")
<<<<<<< HEAD
	public ResponseEntity<?> checkUser(
<<<<<<< HEAD
			@RequestBody @ApiParam(value = "{\"id\": String, \"password\": String}", required = true) Map<String, String> request) {
=======
			@ApiParam("{\"id\": String, \"password\": String}")
			@RequestBody Map<String, String> request) {
>>>>>>> 4e525ea0f9c78020de17bdb0ed1b605dc8918cf3
=======
	public ResponseEntity<?> checkUser(@RequestBody
			@ApiParam(value = "{\"id\": String, \"password\": String}"
			, required = true) Map<String, String> request) {
>>>>>>> parent of 4e525ea (PostController Post CRUD)
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(request.get("id"), request.get("password"));
			if (loginUser != null) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "비밀번호 찾기", notes = "id와 phone을 받아서 user 객체를 반환", response = Map.class)
	@PostMapping("/findpassword")
<<<<<<< HEAD
	public ResponseEntity<?> checkForPassword(
<<<<<<< HEAD
			@RequestBody @ApiParam(value = "{\"id\": String, \"phone\": String}", required = true) Map<String, String> request) {
=======
			@ApiParam("{\"id\": String, \"phone\": String}")
			@RequestBody Map<String, String> request) {
>>>>>>> 4e525ea0f9c78020de17bdb0ed1b605dc8918cf3
=======
	public ResponseEntity<?> checkForPassword(@RequestBody
			@ApiParam(value = "{\"id\": String, \"phone\": String}"
			, required = true) Map<String, String> request) {
>>>>>>> parent of 4e525ea (PostController Post CRUD)
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto member = memberService.select(request.get("id"));
			if (member != null && request.get("phone").equals(member.getPhone())) {
				resultMap.put("user", member);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메시지를 반환", response = Map.class)
	@PostMapping("/login")
<<<<<<< HEAD
	public ResponseEntity<?> login(
<<<<<<< HEAD
			@RequestBody @ApiParam(value = "{\"id\": String, \"password\": String}", required = true) Map<String, String> request) {
=======
			@ApiParam("{\"id\": String, \"password\": String}")
			@RequestBody Map<String, String> request) {
>>>>>>> 4e525ea0f9c78020de17bdb0ed1b605dc8918cf3
=======
	public ResponseEntity<?> login(@RequestBody
			@ApiParam(value = "{\"id\": String, \"password\": String}"
			, required = true) Map<String, String> request) {
>>>>>>> parent of 4e525ea (PostController Post CRUD)
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(request.get("id"), request.get("password"));
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getId(), "access-token");
				logger.debug("로그인 토큰 정보: {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.debug("로그인 실패: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환", response = Map.class)
	@GetMapping("/info/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("id") @ApiParam(value = "인증할 회원의 아이디", required = true) String id,
			HttpServletRequest request) {
		logger.debug("userid : {} ", id);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				MemberDto memberDto = memberService.select(id);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원가입", response = Map.class)
	@PostMapping
<<<<<<< HEAD
	public ResponseEntity<?> insert(@RequestBody @ApiParam(value = "MemberDto", required = true) MemberDto member) {
=======
	public ResponseEntity<?> insert(@RequestBody
<<<<<<< HEAD
			@ApiParam("MemberDto") MemberDto member){
>>>>>>> 4e525ea0f9c78020de17bdb0ed1b605dc8918cf3
=======
			@ApiParam(value = "MemberDto"
			, required = true) MemberDto member){
>>>>>>> parent of 4e525ea (PostController Post CRUD)
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			if (memberService.insert(member)) {
				logger.debug("회원가입 성공 : {}", member);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				logger.debug("회원가입 실패 : {}", member);
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원수정", response = Map.class)
	@PutMapping
<<<<<<< HEAD
	public ResponseEntity<?> update(@RequestBody @ApiParam(value = "MemberDto", required = true) MemberDto member) {
=======
	public ResponseEntity<?> update(@RequestBody
<<<<<<< HEAD
			@ApiParam("MemberDto") MemberDto member){
>>>>>>> 4e525ea0f9c78020de17bdb0ed1b605dc8918cf3
=======
			@ApiParam(value = "MemberDto"
			, required = true) MemberDto member){
>>>>>>> parent of 4e525ea (PostController Post CRUD)
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			if (memberService.update(member)) {
				logger.debug("회원수정 성공 : {}", member);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				logger.debug("회원수정 실패 : {}", member);
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원탈퇴", response = Map.class)
	@DeleteMapping("/{id}")
<<<<<<< HEAD
	public ResponseEntity<?> delete(@PathVariable("id") @ApiParam(value = "id as String", required = true) String id) {
=======
	public ResponseEntity<?> delete(@PathVariable("id")
<<<<<<< HEAD
			@ApiParam("id as String") String id){
>>>>>>> 4e525ea0f9c78020de17bdb0ed1b605dc8918cf3
=======
			@ApiParam(value = "id as String"
			, required = true) String id){
>>>>>>> parent of 4e525ea (PostController Post CRUD)
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			if (memberService.delete(id)) {
				logger.debug("회원삭제 성공 : id = {}", id);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				logger.debug("회원삭제 실패 : id = {}", id);
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 목록", notes = "회원 목록을 반환", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> getInfo() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<MemberDto> userlist = memberService.selectAll();
			if (userlist != null) {
				logger.debug("회원 목록 조회 성공");
				resultMap.put("userlist", userlist);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				logger.debug("회원 목록 조회 실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
