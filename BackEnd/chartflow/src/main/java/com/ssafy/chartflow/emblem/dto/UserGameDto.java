package com.ssafy.chartflow.emblem.dto;

import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.emblem.service.EmblemService;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.game.entity.GameTurns;
import com.ssafy.chartflow.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGameDto{

    private User user;
    private GameTurns gameTurns;
    private GameHistory gameHistory;
    private List<UserEmblem> emblems;
}
