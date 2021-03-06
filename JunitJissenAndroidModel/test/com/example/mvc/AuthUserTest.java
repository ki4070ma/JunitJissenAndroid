package com.example.mvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class AuthUserTest {

	@Test
	public void userIdとpasswordが4文字以上のとき_正しく設定されていること() throws Exception {
		String userId = "userId";
		String password = "password";
		AuthUser instance = new AuthUser(userId, password);
		assertThat(instance.userId, is(userId));
		assertThat(instance.password, is(password));

	}

	@Test(expected = IllegalArgumentException.class)
	public void userIdがnullの場合に例外が発生すること() {
		new AuthUser(null, "password");
	}
}
