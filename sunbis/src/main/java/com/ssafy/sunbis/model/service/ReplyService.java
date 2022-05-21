package com.ssafy.sunbis.model.service;

import com.ssafy.sunbis.model.dto.ReplyDto;

public interface ReplyService {
	boolean insert(ReplyDto reply) throws Exception;
	boolean update(ReplyDto reply) throws Exception;
	boolean delete(int replyno) throws Exception;
}
