package com.ssafy.sunbis.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sunbis.exception.PostLikeException;
import com.ssafy.sunbis.model.mapper.PostMapper;

@Service
public class PostLikeServiceImpl implements PostLikeService {
	
	private final PostMapper postMapper;
	
	@Autowired
	public PostLikeServiceImpl(PostMapper postMapper) {
		this.postMapper = postMapper;
	}

	@Override
	public Map<String, Integer> likeCounts(int postno) throws Exception {
		return postMapper.likeCounts(postno);
	}

	@Override
	public int selectLike(Map<String, Object> map) throws Exception {
		return postMapper.selectLike(map);
	}

	@Override
	public boolean insertLike(Map<String, Object> map) throws Exception {
		int position = (int) map.get("position");
		if ((position != 1 && position != -1) || selectLike(map) != 0) {
			throw new PostLikeException();
		}
		return postMapper.insertLike(map) == 1;
	}

	@Override
	public boolean updateLike(Map<String, Object> map) throws Exception {
		int position = (int) map.get("position");
		if ((position != 1 && position != -1) || selectLike(map) != position * -1) {
			throw new PostLikeException();
		}
		return postMapper.updateLike(map) == 1;
	}

	@Override
	public boolean deleteLike(Map<String, Object> map) throws Exception {
		if (selectLike(map) == 0) {
			throw new PostLikeException();
		}
		return postMapper.deleteLike(map) == 1;
	}

}
