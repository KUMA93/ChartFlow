package com.ssafy.chartflow.user.repository;

import com.ssafy.chartflow.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> { //JpaRepository<Entity클래스, PK타입>

    User findUserByUserEmailAndUserCancel(String userEmail, int userCancel);

    User findUserByUserEmail(String userEmail);

    List<User> findAllByUserCancel(int userCancel); //탈퇴한 회원확인하는 메소드

    User findUserByUserNameAndUserCancel(String userName, int userCancel);

    List<User> findAllByUserNameAndUserCancel(String userName, int userCancel);

    User findUserByUserTokenAndUserCancel(String userToken, int userCancel);

}
