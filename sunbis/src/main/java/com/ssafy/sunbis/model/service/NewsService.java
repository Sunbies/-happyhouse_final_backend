package com.ssafy.sunbis.model.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;

public interface NewsService {
	String get(String apiUrl, Map<String, String> requestHeaders);
	HttpURLConnection connect(String apiUrl);
	String readBody(InputStream body);
}
