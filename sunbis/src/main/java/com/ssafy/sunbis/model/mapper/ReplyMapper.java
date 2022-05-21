package com.ssafy.sunbis.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.sunbis.model.dto.ReplyDto;

@Repository
@Mapper
public interface ReplyMapper {
	int insert(ReplyDto reply);
	int update(ReplyDto reply);
	int delete(int replyno);
}
