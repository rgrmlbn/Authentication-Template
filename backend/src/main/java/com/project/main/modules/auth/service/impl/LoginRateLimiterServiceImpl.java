package com.project.main.modules.auth.service.impl;

import com.project.main.exception.TooManyRequestsException;
import com.project.main.modules.auth.service.interfaces.LoginRateLimiterService;
import com.project.main.security.util.IpExtractor;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class LoginRateLimiterServiceImpl implements LoginRateLimiterService {

    private final ProxyManager<String> proxyManager;
    private final IpExtractor ipExtractor;
    private final com.Rogerd3v.main.config.LoginRateLimitProperties loginRateLimitProperties;

    @Override
    public void checkLimits(String email) {
        checkEmailLimit(email);
        checkIpLimit(ipExtractor.getClientIp());
    }

    private void checkEmailLimit(String email) {
        var cfg = loginRateLimitProperties.getEmail();
        check("rate_limit:email:" + email, cfg.getCapacity(), Duration.ofMinutes(cfg.getRefillMinutes()));
    }

    private void checkIpLimit(String ip) {
        var cfg = loginRateLimitProperties.getIp();
        check("rate_limit:ip:" + ip, cfg.getCapacity(), Duration.ofMinutes(cfg.getRefillMinutes()));
    }

    private void check(String key, int capacity, Duration window) {
        Supplier<BucketConfiguration> config = () -> BucketConfiguration.builder()
                .addLimit(Bandwidth.builder()
                        .capacity(capacity)
                        .refillIntervally(capacity, window)
                        .build())
                .build();

        var bucket = proxyManager.builder().build(key, config);
        if (!bucket.tryConsume(1)) {
            throw new TooManyRequestsException();
        }
    }
}