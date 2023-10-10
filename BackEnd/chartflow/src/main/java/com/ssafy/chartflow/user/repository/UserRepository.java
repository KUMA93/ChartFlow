package com.ssafy.chartflow.user.repository;

import com.ssafy.chartflow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository { //JpaRepository<Entity클래스, PK타입>

    User findUserByEmail(String email);

    User findUserById(long userId);

    User findUserByNickname(String nickname);

    User findByEmail(String email);

}
