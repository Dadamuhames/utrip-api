package com.msd.utrip.service.auth.token;

import com.msd.utrip.dto.response.TokenResponse;
import com.msd.utrip.entity.RefreshTokenEntity;
import com.msd.utrip.entity.base.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public TokenResponse createPair(final CustomUserDetails user) {
        String accessToken = jwtService.generateToken(user);

        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(user);

        return new TokenResponse(accessToken, refreshToken.getToken());
    }
}
