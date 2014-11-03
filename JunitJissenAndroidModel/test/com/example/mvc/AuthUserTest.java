package com.example.mvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class AuthUserTest {

	@Test
	public void userId��password��4�����ȏ�̂Ƃ�_�������ݒ肳��Ă��邱��() throws Exception {
		String userId = "userId";
		String password = "password";
		AuthUser instance = new AuthUser(userId, password);
		assertThat(instance.userId, is(userId));
		assertThat(instance.password, is(password));

	}

	@Test(expected = IllegalArgumentException.class)
	public void userId��null�̏ꍇ�ɗ�O���������邱��() {
		new AuthUser(null, "password");
	}
}
