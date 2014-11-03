package com.example.mvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class AuthServiceImplTest {

	AuthServiceImpl sut;
	JsonHttpClient httpClient;
	AuthUser authUser;
	Map<String, String> params;

	@Before
	public void setup() throws Exception {
		sut = new AuthServiceImpl();
		httpClient = mock(JsonHttpClient.class);
		sut.httpClient = httpClient;
		authUser = new AuthUser("u000001", "123456");
		params = new HashMap<String, String>();
		params.put("userId", "u000001");
		params.put("password", "123456");
	}

	@Test(expected = IllegalArgumentException.class)
	public void authUser‚ªnull‚Ì‚Æ‚«_—áŠO() throws Exception {
		sut.login(null);
	}

	@Test
	public void httpClient‚ªsuccess‚ğŠÜ‚ŞJSON‚ğ•Ô‚·‚Æ‚«‚Étrue‚ğ•Ô‚·‚±‚Æ() throws Exception {
		when(httpClient.sendRequest("http://localhost/api/login", params))
				.thenReturn("{ result: 'success'}");
		assertThat(sut.login(authUser), is(true));
	}

	@Test
	public void httpClient‚ªfail‚ğŠÜ‚ŞJSON‚ğ•Ô‚·‚Æ‚«‚Éfalse‚ğ•Ô‚·‚±‚Æ() throws Exception {
		when(httpClient.sendRequest("http://localhost/api/login", params))
				.thenReturn("{result:'fail'}");
		assertThat(sut.login(authUser), is(false));

	}

	@Test(expected = IOException.class)
	public void httpClient‚ªIOException‚ğ‘—o‚·‚é‚Æ‚«‚»‚Ì‚Ü‚Ü‘—o‚·‚é() throws Exception {
		when(httpClient.sendRequest("http://localhost.api/login", params))
				.thenThrow(new IOException());
		sut.login(authUser);
	}
}
