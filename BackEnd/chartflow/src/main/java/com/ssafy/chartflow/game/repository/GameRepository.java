package com.ssafy.chartflow.game.repository;

import com.ssafy.chartflow.game.dto.projection.GameHistoryProjection;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<GameHistory, Long> {

    GameHistory findGameHistoryById(long gameHistoryId);

    List<GameHistory> findGameHistoriesByUserId(long userId);

}
