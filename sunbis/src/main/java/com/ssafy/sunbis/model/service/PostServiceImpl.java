package com.ssafy.sunbis.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.sunbis.model.dto.PostDto;
import com.ssafy.sunbis.model.mapper.PostMapper;

public class PostServiceImpl implements PostService {
	
	private final PostMapper postMapper;
	
	@Autowired
	public PostServiceImpl(PostMapper postMapper) {
		this.postMapper = postMapper;
	}
	
	@Override
	public List<PostDto> list(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		String word = map.get("word");
		param.put("word", word == null ? "" : word);
		
		String orderby = map.get("orderby");
		param.put("orderby", orderby == null ? "postno" : orderby);
		param.put("order", "desc".equals(map.get("order")) ? "desc" : "asc");
		
		int currentPageNo = Integer.parseInt(map.get("pg"));
		int countPerPage = Integer.parseInt(map.get("cpp"));
		int start = (currentPageNo - 1) * countPerPage;
		
		param.put("start", start);
		param.put("cpp", countPerPage);
		
		return postMapper.list(param);
	}
}



















