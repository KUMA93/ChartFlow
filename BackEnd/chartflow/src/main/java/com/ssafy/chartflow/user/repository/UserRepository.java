package com.ssafy.chartflow.user.repository;

import com.ssafy.chartflow.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> { //JpaRepository<Entity클래스, PK타입>

    User findUserByEmailAndCancel(String email, int cancel);

    User findUserByUserId(long userId);

    User findUserByRefreshToken_token(String refreshToken);

    User findByEmail(String email);

}
