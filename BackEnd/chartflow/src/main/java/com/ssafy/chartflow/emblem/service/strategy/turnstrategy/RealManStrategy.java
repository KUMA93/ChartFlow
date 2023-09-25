package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.service.strategy.GameStrategy;

public class RealManStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        //1판에 풀매수 3회 이상
        return false;
    }

    @Override
    public String getTitle() {
        return "물가상승은 조상님이 내주냐?";
    }

}
