package com.example.mvc;

import java.io.IOException;

public class AuthServiceStub implements AuthService {

	@Override
	public boolean login(AuthUser authUser) throws IOException {

		return (authUser.userId.equals("duke") && authUser.password
				.equals("3micro"));
	}
}
