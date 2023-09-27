package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.user.entity.User;

import java.util.List;

public class BanpoXiStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 자산이 30억 이상
        User user = userGameDto.getUser();
        List<UserEmblem> emblems = userGameDto.getEmblems();

        long condition = 30000000000L;
        if (user.getBudget() >= condition){
            return emblems.stream()
                    .noneMatch(emblem -> "반포자이".equals(emblem.getEmblem().getName()));
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "반포자이";
    }

}
