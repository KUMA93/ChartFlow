package com.ssafy.chartflow.security.repository;

import com.ssafy.chartflow.security.entity.RefreshToken;
import com.ssafy.chartflow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    public RefreshToken findByToken(String token);

    public void deleteByToken(String token);

}
