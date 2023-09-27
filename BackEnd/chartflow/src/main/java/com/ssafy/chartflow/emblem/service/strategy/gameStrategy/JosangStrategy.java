package com.ssafy.chartflow.emblem.service.strategy.gameStrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.game.entity.GameTurns;

import java.util.List;

public class JosangStrategy implements GameStrategy{
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 게임 종료 후 매수매도하고도 수익률 0프로대
        // GameHistory테이블에 진행한 게임에 대한 rate값이 0점대이면 return true

        GameHistory gameHistory = userGameDto.getGameHistory();
        List<GameTurns> gameTurns = gameHistory.getGameTurns();
        List<UserEmblem> emblems = userGameDto.getEmblems();
        double rate = gameHistory.getRate();

        boolean hasEmblem = emblems.stream()
                .anyMatch(emblem -> "물가상승은 조상님이".equals(emblem.getEmblem().getName()));
        if (hasEmblem) return false;

        return rate < 1.0;
    }

    @Override
    public String getTitle() {
        return "물가상승은 조상님이";
    }

}
