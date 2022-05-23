package com.ssafy.sunbis.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sunbis.model.dto.PostDto;
import com.ssafy.sunbis.model.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService {
	
	private final PostMapper postMapper;
	
	@Autowired
	public PostServiceImpl(PostMapper postMapper) {
		this.postMapper = postMapper;
	}
	
	@Override
	public List<PostDto> list(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		param.putAll(map);
		
		String orderby = map.get("orderby");
		param.put("orderby", orderby == null ? "postno" : orderby);
		param.put("order", "asc".equals(map.get("order")) ? "asc" : "desc");
		
		int currentPageNo = Integer.parseInt(map.get("pg"));
		int countPerPage = Integer.parseInt(map.get("cpp"));
		int start = (currentPageNo - 1) * countPerPage;
		
		param.put("start", start);
		param.put("cpp", countPerPage);
		
		return postMapper.list(param);
	}

	@Override
	public PostDto select(int postno) throws Exception {
		return postMapper.select(postno);
	}

	@Override
	public boolean insert(PostDto post) throws Exception {
		return postMapper.insert(post) == 1;
	}

	@Override
	public boolean update(PostDto post) throws Exception {
		return postMapper.update(post) == 1;
	}

	@Override
	public boolean delete(int postno) throws Exception {
		return postMapper.delete(postno) == 1;
	}
	
}
