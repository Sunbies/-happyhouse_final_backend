package com.ssafy.sunbis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sunbis.model.dto.ReplyDto;
import com.ssafy.sunbis.model.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	private final ReplyMapper replyMapper;
	private final BadWordFilter badWordFilter;
	
	@Autowired
	public ReplyServiceImpl(
			ReplyMapper replyMapper
			, BadWordFilter badWordFilter) {
		this.replyMapper = replyMapper;
		this.badWordFilter = badWordFilter;
	}

	@Override
	public boolean insert(ReplyDto reply) throws Exception {
		reply.setContent(badWordFilter.filter(reply.getContent()));
		return replyMapper.insert(reply) == 1;
	}

	@Override
	public boolean update(ReplyDto reply) throws Exception {
		reply.setContent(badWordFilter.filter(reply.getContent()));
		return replyMapper.update(reply) == 1;
	}

	@Override
	public boolean delete(int replyno) throws Exception {
		return replyMapper.delete(replyno) == 1;
	}

}
