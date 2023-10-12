package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InSeoulStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 총 자산 10억 이상 달성
        User user = userGameDto.getUser();
        List<UserEmblem> emblems = userGameDto.getEmblems();

        long condition = 1000000000L;
        if (user.getBudget() >= condition){
            return emblems.stream()
                    .noneMatch(emblem -> "인서울".equals(emblem.getEmblem().getName()));
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "인서울";
    }

}
