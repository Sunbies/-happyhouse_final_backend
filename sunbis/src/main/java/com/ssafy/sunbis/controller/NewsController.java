package com.ssafy.sunbis.controller;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.sunbis.model.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	private final NewsService newsService;

	@Autowired
	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@GetMapping("/{keyword}")
	public ResponseEntity<String> getNews(@PathVariable("keyword") String keyword) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String clientId = "0X2w4jlyesfcuGXEUJNX";
		String clientSecret = "iH_IjuEeA1";
		String text = null;
		try {
			text = URLEncoder.encode(keyword, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
		String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text; // json 결과
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = newsService.get(apiURL, requestHeaders);
		Object result = responseBody;
		System.out.println(responseBody);
		return new ResponseEntity<String>(responseBody, status);

	}
}
