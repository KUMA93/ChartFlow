package com.ssafy.chartflow.user.repository;

import com.ssafy.chartflow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { //JpaRepository<Entity클래스, PK타입>

    User findUserByEmailAndCancel(String email, int cancel);

    User findUserById(long userId);

    User findUserByRefreshToken_token(String refreshToken);

    User findByEmail(String email);

}
