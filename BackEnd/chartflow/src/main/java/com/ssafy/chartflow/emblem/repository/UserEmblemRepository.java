package com.ssafy.chartflow.emblem.repository;

import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEmblemRepository extends JpaRepository<UserEmblem, Long>{
    public List<UserEmblem> findUserEmblemByUser(User user);

    public UserEmblem findUserEmblemByUserAndEquipedIsTrue(User user);
}
