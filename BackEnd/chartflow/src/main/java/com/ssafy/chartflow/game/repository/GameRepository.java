package com.ssafy.chartflow.game.repository;

import com.ssafy.chartflow.game.entity.GameHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameHistory, Long> {

    GameHistory findGameHistoryById(long gameHistoryId);



}
