package com.example.mvc;
import java.io.IOException;

public interface AuthService {
	boolean login(AuthUser authUser) throws IOException;
}
