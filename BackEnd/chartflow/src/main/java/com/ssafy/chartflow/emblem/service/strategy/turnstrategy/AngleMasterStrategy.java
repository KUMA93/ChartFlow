package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.user.entity.User;

import java.util.List;

public class AngleMasterStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 자산이 00000으로 끝날 때
        User user = userGameDto.getUser();
        List<Emblem> emblems = userGameDto.getEmblems();

        if (user.getBudget()%10000 == 0){
            return emblems.stream()
                    .noneMatch(emblem -> "각도기".equals(emblem.getName()));
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "각도기";
    }

}
