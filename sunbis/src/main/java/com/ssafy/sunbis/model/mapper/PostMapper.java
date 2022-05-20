package com.ssafy.sunbis.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.sunbis.model.dto.PostDto;
import com.ssafy.sunbis.model.dto.ReplyDto;

@Repository
@Mapper
public interface PostMapper {
	List<PostDto> list(Map<String, Object> map);
	PostDto select(@Param("postno") int postno);
	int insert(PostDto post);
	int update(PostDto post);
	int delete(@Param("postno") int postno);
	int insertLike(Map<String, String> map);
	int updateLike(Map<String, String> map);
	int deleteLike(Map<String, String> map);
	List<ReplyDto> replyList(@Param("postno") int postno);
}
