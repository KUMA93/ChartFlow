package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.game.entity.GameTurns;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LeeSeungKiStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 매도 후 연속 5회 주가 하락
        GameHistory gameHistory = userGameDto.getGameHistory();
        List<GameTurns> gameTurns = gameHistory.getGameTurns();
        List<UserEmblem> emblems = userGameDto.getEmblems();

        boolean hasEmblem = emblems.stream()
                .anyMatch(emblem -> "이승기".equals(emblem.getEmblem().getName()));

        if (hasEmblem) return false;

        int cnt = 0;
        int beforePrice = gameTurns.get(0).getTodayPrice();
        boolean isSell = false;

        for (int i = 1; i<gameTurns.size(); i++){
            GameTurns currentTurn = gameTurns.get(i);
            int behavior = currentTurn.getBehavior();
            int currentPrice = currentTurn.getTodayPrice();
            long stocks = currentTurn.getCurrentStocks();

            if (behavior == 1 && stocks == 0){
                isSell = true;
            } else if (behavior != 0) {
                isSell = false;
            }

            if (isSell){
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
        return "이승기";
    }

}
