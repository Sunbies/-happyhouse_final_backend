package com.ssafy.sunbis.model.service;

import java.util.List;

import com.ssafy.sunbis.model.dto.MemberDto;

public interface MemberService {
	MemberDto login(String id, String password) throws Exception;
	List<MemberDto> selectAll() throws Exception;
	MemberDto select(String id) throws Exception;
	boolean insert(MemberDto member) throws Exception;
	boolean update(MemberDto member) throws Exception;
	boolean delete(String id) throws Exception;
}
