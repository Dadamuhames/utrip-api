package com.msd.utrip.service.auth.token;

import com.msd.utrip.config.properties.JwtProperty;
import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.entity.RefreshTokenEntity;
import com.msd.utrip.entity.base.CustomUserDetails;
import com.msd.utrip.exception.RefreshTokenException;
import com.msd.utrip.repository.RefreshTokenRepository;
import com.msd.utrip.service.auth.userdetails.UserDetailDispatcher;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperty jwtProperty;
    private final UserDetailDispatcher detailsServiceDispatcher;

    public RefreshTokenEntity createRefreshToken(final CustomUserDetails userDetails) {
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
            .token(UUID.randomUUID().toString())
            .userRole(userDetails.getUserRole())
            .subject(userDetails.getUsername())
            .expiryDate(Instant.now().plusMillis(jwtProperty.getRefreshTtl()))
            .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshTokenEntity findByToken(final String token) {
        return refreshTokenRepository.findByToken(token).orElseThrow(() -> new RefreshTokenException(ErrorCode.REFRESH_TOKEN_INVALID_CODE));
    }


    public CustomUserDetails getUserDetails(final RefreshTokenEntity refreshToken) {
        return (CustomUserDetails) detailsServiceDispatcher.loadUserByLoginAndRole(refreshToken.getSubject(), refreshToken.getUserRole());
    }


    public void verifyExpiration(final RefreshTokenEntity token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenException(ErrorCode.REFRESH_TOKEN_INVALID_CODE);
        }
    }

    public void expireToken(RefreshTokenEntity token) {
        token.setExpiryDate(Instant.now());
        refreshTokenRepository.save(token);
    }
}
