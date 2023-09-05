package com.ssafy.chartflow.security.service;

import com.ssafy.chartflow.security.entity.RefreshToken;
import com.ssafy.chartflow.security.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    public void delete(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}