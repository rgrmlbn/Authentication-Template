package com.project.main.modules.token.service.interfaces;

import com.project.main.modules.token.entity.RefreshToken;
import com.project.main.modules.user.entity.UserEntity;

public interface RefreshTokenService {

    String createRefreshToken(UserEntity user);

    RefreshToken validateRefreshToken(String rawToken);

    String rotateRefreshToken(RefreshToken oldToken);

    void revokeAllByUser(UserEntity user);

    void deleteAllByUser(UserEntity user);
}
