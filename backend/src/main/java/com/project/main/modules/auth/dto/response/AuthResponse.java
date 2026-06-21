package com.project.main.modules.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
