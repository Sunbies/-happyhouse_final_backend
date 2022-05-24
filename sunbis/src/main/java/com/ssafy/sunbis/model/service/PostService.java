package com.ssafy.sunbis.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.sunbis.model.dto.PostDto;

public interface PostService {
	
	List<PostDto> list(Map<String, String> map) throws Exception;
	PostDto select(int postno) throws Exception;
	boolean insert(PostDto post) throws Exception;
	boolean update(PostDto post) throws Exception;
	boolean delete(int postno) throws Exception;
	
	List<PostDto> listLatestPosts() throws Exception;
	List<PostDto> listHotPosts() throws Exception;
	
}
