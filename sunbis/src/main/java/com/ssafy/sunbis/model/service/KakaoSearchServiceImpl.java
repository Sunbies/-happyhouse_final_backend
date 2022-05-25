package com.ssafy.sunbis.model.service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class KakaoSearchServiceImpl implements KakaoSearchService {
	
	public static final Logger logger = LoggerFactory.getLogger(KakaoSearchServiceImpl.class);

	private final String baseUrl;
	private final String apiKey;

	public KakaoSearchServiceImpl(@Value("${api-key.kakao.rest-api}") String apiKey,
			@Value("${base-url.kakao-image-search}") String baseUrl) {
		this.apiKey = apiKey;
		this.baseUrl = baseUrl;
	}

	@Override
	public String getImageUrl(String word) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "KakaoAK " + apiKey);
		HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
		URI requestUri = UriComponentsBuilder.fromUriString(baseUrl).queryParam("query", word).queryParam("size", 1)
				.build().encode(StandardCharsets.UTF_8).toUri();
		
		logger.debug("URI: {}", requestUri);

		@SuppressWarnings("unchecked")
		String imageUrl = ((Map<String, String>) ((List<Object>) (restTemplate
				.exchange(requestUri, HttpMethod.GET, httpEntity, Map.class).getBody()).get("documents")).get(0))
						.get("image_url");

		return imageUrl;
	}

}
