package com.ssafy.chartflow.emblem.service.strategy.gameStrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.game.entity.GameTurns;

import java.util.List;

public class LowBoyStrategy implements GameStrategy{
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 매수&매도 없음
        // game turns테이블에 진행한 게임에 대한 값이 존재하지 않는 경우 return true

        GameHistory gameHistory = userGameDto.getGameHistory();
        List<GameTurns> gameTurns = gameHistory.getGameTurns();
        List<UserEmblem> emblems = userGameDto.getEmblems();

        boolean hasEmblem = emblems.stream()
                .anyMatch(emblem -> "하남자".equals(emblem.getEmblem().getName()));
        if (hasEmblem) return false;

        for (GameTurns turns: gameTurns){
            if (turns.getBehavior() != 2)
                return false;
        }
        return true;
    }

    @Override
    public String getTitle() {
        return "하남자";
    }
}
