package com.example.mvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class AuthUserTest {

	@Test
	public void userId‚Æpassword‚ª4•¶šˆÈã‚Ì‚Æ‚«_³‚µ‚­İ’è‚³‚ê‚Ä‚¢‚é‚±‚Æ() throws Exception {
		String userId = "userId";
		String password = "password";
		AuthUser instance = new AuthUser(userId, password);
		assertThat(instance.userId, is(userId));
		assertThat(instance.password, is(password));

	}

	@Test(expected = IllegalArgumentException.class)
	public void userId‚ªnull‚Ìê‡‚É—áŠO‚ª”­¶‚·‚é‚±‚Æ() {
		new AuthUser(null, "password");
	}
}
