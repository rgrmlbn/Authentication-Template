package com.project.main.modules.auth.service.interfaces;

public interface LoginRateLimiterService {
    void checkLimits(String email);
}