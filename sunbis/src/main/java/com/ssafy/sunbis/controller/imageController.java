package com.ssafy.sunbis.controller;

import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sunbis.model.service.KakaoSearchService;

@RestController
@RequestMapping("/image")
public class imageController {

	public static final Logger logger = LoggerFactory.getLogger(imageController.class);

	private final KakaoSearchService kakaoSearchService;

	@Autowired
	public imageController(KakaoSearchService kakaoSearchService) {
		this.kakaoSearchService = kakaoSearchService;
	}

	@ResponseBody
	@GetMapping("/kakao/{word}")
	public byte[] getFirstImage(@PathVariable("word") String word) {
		logger.debug("getFirstImage: {}", word);
		try {
			URL url = new URL(kakaoSearchService.getImageUrl(word));
			try (InputStream is = url.openStream();) {
				return IOUtils.toByteArray(is);
			}
		} catch (Exception e) {
			logger.debug("getFirstImage 오류: {}", e.getMessage());
		}
		return null;
	}

}
