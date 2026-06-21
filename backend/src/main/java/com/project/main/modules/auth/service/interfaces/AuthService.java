package com.project.main.modules.auth.service.interfaces;

import com.project.main.modules.auth.dto.request.LoginRequest;
import com.project.main.modules.auth.dto.request.RefreshTokenRequest;
import com.project.main.modules.auth.dto.request.RegisterRequest;
import com.project.main.modules.auth.dto.response.AuthResponse;
import com.project.main.modules.auth.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);

    void logout();
}
