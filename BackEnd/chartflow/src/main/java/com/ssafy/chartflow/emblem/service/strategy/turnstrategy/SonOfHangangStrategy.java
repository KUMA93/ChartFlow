package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.service.strategy.GameStrategy;
import com.ssafy.chartflow.user.entity.User;

import java.util.List;

public class SonOfHangangStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 전체 자산 1000만원 이하
        User user = userGameDto.getUser();
        List<Emblem> emblems = userGameDto.getEmblems();

        long condition = 10000000L;
        if (user.getBudget() <= condition){
            return emblems.stream()
                    .noneMatch(emblem -> "한강의아들".equals(emblem.getName()));
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "한강의아들";
    }

}