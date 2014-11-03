package com.example.mvc;

import java.io.IOException;
import java.util.Map;

public interface JsonHttpClient {
	String sendRequest(String url, Map<String, String> params)
			throws IOException;
}
