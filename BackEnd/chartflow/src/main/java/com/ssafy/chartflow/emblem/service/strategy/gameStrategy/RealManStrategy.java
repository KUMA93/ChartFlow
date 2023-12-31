package com.ssafy.chartflow.emblem.service.strategy.gameStrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.emblem.service.strategy.turnstrategy.TurnStrategy;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.game.entity.GameTurns;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RealManStrategy implements GameStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 풀매수 3회
        List<UserEmblem> emblems = userGameDto.getEmblems();
        GameHistory gameHistory = userGameDto.getGameHistory();
        List<GameTurns> gameTurns = gameHistory.getGameTurns();

        boolean hasEmblem = emblems.stream()
                .anyMatch(emblem -> "상남자".equals(emblem.getEmblem().getName()));

        if (hasEmblem) return false;

        int cnt = 0;
        for (GameTurns gameturn : gameTurns){
            if(gameturn.getTotalAssets() - gameturn.getCurrentStocks() < gameturn.getPrice())
                ++cnt;
        }
        return cnt >= 2;
    }

    @Override
    public String getTitle() {
        return "상남자";
    }

}
