package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.game.entity.GameTurns;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoRegretStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 1판에 매수 후 연속 5회 주가 하락
        GameHistory gameHistory = userGameDto.getGameHistory();
        List<GameTurns> gameTurns = gameHistory.getGameTurns();
        List<UserEmblem> emblems = userGameDto.getEmblems();

        boolean hasEmblem = emblems.stream()
                .anyMatch(emblem -> "징크스".equals(emblem.getEmblem().getName()));

        if (hasEmblem) return false;

        int cnt = 0;
        int beforePrice = gameTurns.get(0).getTodayPrice();
        boolean isBuy = false;

        for (int i = 1; i<gameTurns.size(); i++){
            GameTurns currentTurn = gameTurns.get(i);
            int behavior = currentTurn.getBehavior();
            int currentPrice = currentTurn.getTodayPrice();
            long stocks = currentTurn.getCurrentStocks();

            if (behavior == 0){
                isBuy = true;
            } else if (stocks != 0) {
                isBuy = false;
            }

            if (isBuy){
                if (currentPrice <= beforePrice){
                    ++cnt;
                }
            }

            if (cnt >= 5)
                return true;
            beforePrice = currentPrice;
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "징크스";
    }

}
