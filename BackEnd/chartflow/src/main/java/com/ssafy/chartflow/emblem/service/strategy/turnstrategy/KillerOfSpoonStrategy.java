package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.emblem.service.strategy.GameStrategy;
import com.ssafy.chartflow.game.entity.GameHistory;
import com.ssafy.chartflow.user.entity.User;

import java.util.List;

public class KillerOfSpoonStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 5% 이내 수익률 20회 이상
        User user = userGameDto.getUser();
        List<GameHistory> histories = user.getGameHistories();
        List<UserEmblem> emblems = userGameDto.getEmblems();

        long condition = 1000000000L;
        int count = (int)histories.stream().filter(history -> history.getRate() >= 5.0).count();

        if (count >= 20){
            return emblems.stream()
                    .noneMatch(emblem -> "숟가락살인마".equals(emblem.getEmblem().getName()));
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "숟가락살인마";
    }

}
