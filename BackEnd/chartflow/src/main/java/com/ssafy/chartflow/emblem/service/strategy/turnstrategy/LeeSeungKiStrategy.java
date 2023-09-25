package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.service.strategy.GameStrategy;

public class LeeSeungKiStrategy implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 매도 후 연속 5회 주가 하락

        return false;
    }

    @Override
    public String getTitle() {
        return "물가상승은 조상님이 내주냐?";
    }

}
