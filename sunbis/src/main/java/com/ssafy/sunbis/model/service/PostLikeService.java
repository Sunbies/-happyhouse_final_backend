package com.ssafy.sunbis.model.service;

import java.util.Map;

public interface PostLikeService {
	Map<String, Integer> likeCounts(int postno) throws Exception;
	int selectLike(Map<String, Object> map) throws Exception;
	boolean insertLike(Map<String, Object> map) throws Exception;
	boolean updateLike(Map<String, Object> map) throws Exception;
	boolean deleteLike(Map<String, Object> map) throws Exception;
}
