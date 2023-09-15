package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.service.strategy.GameStrategy;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.game.entity.GameTurns;

import java.util.List;

public class NoRegretStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 1판에 매수 후 연속 5회 주가 하락
        GameHistory gameHistory = userGameDto.getGameHistory();
        List<GameTurns> gameTurns = gameHistory.getGameTurns();
        List<Emblem> emblems = userGameDto.getEmblems();

        boolean hasEmblem = emblems.stream()
                .anyMatch(emblem -> "이승기".equals(emblem.getName()));

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
        return "물가상승은 조상님이 내주냐?";
    }

}
