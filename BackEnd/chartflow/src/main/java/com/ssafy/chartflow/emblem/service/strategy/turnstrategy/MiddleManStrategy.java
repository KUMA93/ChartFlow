package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.game.entity.GameTurns;

import java.util.List;

public class MiddleManStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 풀매수 3회
        List<Emblem> emblems = userGameDto.getEmblems();
        GameHistory gameHistory = userGameDto.getGameHistory();
        List<GameTurns> gameTurns = gameHistory.getGameTurns();

        boolean hasEmblem = emblems.stream()
                .anyMatch(emblem -> "중남자".equals(emblem.getName()));

        if (hasEmblem) return false;

        int cnt = 0;
        for (GameTurns gameturn : gameTurns){
            if(gameturn.getTotalAssets() - gameturn.getCurrentStocks() < gameturn.getPrice())
                ++cnt;
        }
        return cnt >= 1;
    }

    @Override
    public String getTitle() {
        return "중남자";
    }

}
