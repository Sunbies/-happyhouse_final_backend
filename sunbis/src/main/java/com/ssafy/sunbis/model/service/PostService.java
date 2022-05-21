package com.ssafy.sunbis.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.sunbis.model.dto.PostDto;

public interface PostService {
	
	List<PostDto> list(Map<String, String> map) throws Exception;
	
}
