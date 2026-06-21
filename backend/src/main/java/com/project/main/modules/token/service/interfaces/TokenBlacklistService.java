package com.project.main.modules.token.service.interfaces;

public interface TokenBlacklistService {
    void blacklist(String token);
    boolean isBlacklisted(String token);
}