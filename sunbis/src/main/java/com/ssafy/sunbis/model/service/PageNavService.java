package com.ssafy.sunbis.model.service;

import java.util.Map;

import com.ssafy.sunbis.model.dto.PageNavDto;

public interface PageNavService {
	PageNavDto getPageNav(Map<String, String> map) throws Exception;
	PageNavDto getPageNav(Map<String, String> map, int totalCount) throws Exception;
}
