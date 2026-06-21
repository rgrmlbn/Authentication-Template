package com.project.auth.modules.auth.service.interfaces;

public interface LoginRateLimiterService {
    void checkLimits(String email);
}