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
	
	/**
	 * map이 반드시 포함할 것:
	 * boardtype, orderby(칼럼명), order(asc, desc), start, cpp
	 * 
	 * map이 포함할 수 있는 것:
	 * key, word (어느 하나라도 포함되려면 둘 모두 포함돼야 함)
	 * 
	 * @param map
	 * @return post list
	 */
	List<PostDto> list(Map<String, Object> map);
	
	/**
	 * 어떤 한 게시글의 댓글 리스트를 반환
	 * list 쿼리 내부에서 호출됨
	 */
	List<ReplyDto> replyList(@Param("postno") int postno);
	
	/**
	 * @param postno
	 * @return post
	 */
	PostDto select(@Param("postno") int postno);
	
	/**
	 * @param post
	 * @return 1 success, 0 fail
	 */
	int insert(PostDto post);
	
	/**
	 * @param post
	 * @return 1 success, 0 fail
	 */
	int update(PostDto post);
	
	/**
	 * @param postno
	 * @return 1 success, 0 fail
	 */
	int delete(@Param("postno") int postno);
	
	/**
	 * 어떤 한 게시글에 대한 like 카운트를 반환
	 * @return { likeCount: int, dislikeCount: int }
	 */
	Map<String, Integer> likeCounts(@Param("postno") int postno);
	
	/**
	 * 어떤 한 게시글에 대한 어떤 한 유저가 표시한 like 포지션을 반환
	 * map이 반드시 포함할 것:
	 * id, postno
	 * 
	 * @param map
	 * @return 0 없음, 1 좋아요, -1 싫어요
	 */
	int selectLike(Map<String, Object> map);
	
	/**
	 * map이 반드시 포함할 것:
	 * id, postno, position
	 * 
	 * @param map
	 * @return 1 success, 0 fail
	 */
	int insertLike(Map<String, Object> map);
	
	/**
	 * map이 반드시 포함할 것:
	 * id, postno, position
	 * 
	 * @param map
	 * @return 1 success, 0 fail
	 */
	int updateLike(Map<String, Object> map);
	
	/**
	 * map이 반드시 포함할 것:
	 * id, postno
	 * 
	 * @param map
	 * @return 1 success, 0 fail
	 */
	int deleteLike(Map<String, Object> map);
	
}
