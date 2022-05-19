package com.ssafy.sunbis.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sunbis.model.dto.MemberDto;
import com.ssafy.sunbis.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		return memberMapper.login(id, password);
	}

	@Override
	public List<MemberDto> selectAll() throws Exception {
		return memberMapper.selectAll();
	}

	@Override
	public MemberDto select(String id) throws Exception {
		return memberMapper.select(id);
	}

	@Override
	public boolean insert(MemberDto member) throws Exception {
		return memberMapper.insert(member) == 1;
	}

	@Override
	public boolean update(MemberDto member) throws Exception {
		return memberMapper.update(member) == 1;
	}

	@Override
	public boolean delete(String id) throws Exception {
		return memberMapper.delete(id) == 1;
	}
	
}
