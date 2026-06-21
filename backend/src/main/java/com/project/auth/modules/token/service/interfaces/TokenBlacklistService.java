package com.project.auth.modules.token.service.interfaces;

public interface TokenBlacklistService {
    void blacklist(String token);
    boolean isBlacklisted(String token);
}