package com.example.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthServiceImpl implements AuthService {

	private static final String URL = "http://localhost/api/login";

	// TODO Žg—pŠm’èŒã‚ÉŽÀ‘•
	JsonHttpClient httpClient = null;

	@Override
	public boolean login(AuthUser authUser) throws IOException {
		if (authUser == null)
			throw new IllegalArgumentException();
		String json = httpClient.sendRequest(URL, toParams(authUser));
		return json.toLowerCase().contains("success");
	}

	private Map<String, String> toParams(AuthUser authUser) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", authUser.userId);
		params.put("password", authUser.password);
		return params;
	}
}
