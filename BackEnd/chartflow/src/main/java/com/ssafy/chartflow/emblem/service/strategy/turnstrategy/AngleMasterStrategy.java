package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AngleMasterStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 자산이 00000으로 끝날 때
        User user = userGameDto.getUser();
        List<UserEmblem> emblems = userGameDto.getEmblems();

        if (user.getBudget()%10000 == 0){
            return emblems.stream()
                    .noneMatch(emblem -> "각도기".equals(emblem.getEmblem().getName()));
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "각도기";
    }

}
