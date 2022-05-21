package com.ssafy.sunbis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sunbis.model.dto.ReplyDto;
import com.ssafy.sunbis.model.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	private final ReplyMapper replyMapper;
	
	@Autowired
	public ReplyServiceImpl(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}

	@Override
	public boolean insert(ReplyDto reply) throws Exception {
		return replyMapper.insert(reply) == 1;
	}

	@Override
	public boolean update(ReplyDto reply) throws Exception {
		return replyMapper.update(reply) == 1;
	}

	@Override
	public boolean delete(int replyno) throws Exception {
		return replyMapper.delete(replyno) == 1;
	}

}
