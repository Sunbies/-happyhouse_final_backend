package com.ssafy.sunbis.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sunbis.model.dto.PageNavDto;
import com.ssafy.sunbis.model.mapper.PageNavMapper;

@Service
public class PageNavServiceImpl implements PageNavService {
	
	private final PageNavMapper pageNavMapper;
	
	@Autowired
	public PageNavServiceImpl(PageNavMapper pageNavMapper) {
		this.pageNavMapper = pageNavMapper;
	}

	@Override
	public PageNavDto getPageNav(Map<String, String> map) throws Exception {
		PageNavDto pageNav = new PageNavDto();
		
		int navSize = 10;
		int currentPageNo = Integer.parseInt(map.get("pg"));
		int countPerPage = Integer.parseInt(map.get("cpp"));
		int totalCount = pageNavMapper.getTotalCount(map);
		int totalPageCount = (totalCount - 1) / countPerPage + 1;
		int startPageNo = (currentPageNo - 1) / navSize * navSize + 1;
		int endPageNo = startPageNo + navSize - 1;
		if(endPageNo > totalPageCount) endPageNo = totalPageCount;
		
		pageNav.setCurrentPageNo(currentPageNo);
		pageNav.setStartPageNo(startPageNo);
		pageNav.setEndPageNo(endPageNo);
		pageNav.setTotalPageCount(totalPageCount);
		pageNav.setHeadRange(currentPageNo <= navSize);
		pageNav.setTailRange((totalPageCount - 1) / navSize * navSize < currentPageNo);
		
		return pageNav;
	}

	@Override
	public PageNavDto getPageNav(Map<String, String> map, int totalCount) throws Exception {
		PageNavDto pageNav = new PageNavDto();
		
		int navSize = 5;
		int currentPageNo = Integer.parseInt(map.get("pg"));
		int countPerPage = Integer.parseInt(map.get("cpp"));
		int totalPageCount = (totalCount - 1) / countPerPage + 1;
		int startPageNo = (currentPageNo - 1) / navSize * navSize + 1;
		int endPageNo = startPageNo + navSize - 1;
		if(endPageNo > totalPageCount) endPageNo = totalPageCount;
		
		pageNav.setCurrentPageNo(currentPageNo);
		pageNav.setStartPageNo(startPageNo);
		pageNav.setEndPageNo(endPageNo);
		pageNav.setTotalPageCount(totalPageCount);
		pageNav.setHeadRange(currentPageNo <= navSize);
		pageNav.setTailRange((totalPageCount - 1) / navSize * navSize < currentPageNo);
		
		return pageNav;
	}

}
