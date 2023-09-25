package com.ssafy.chartflow.emblem.service.strategy.turnstrategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.service.strategy.GameStrategy;

public class ShortPunchInsectTechnology implements TurnStrategy {
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        //한 판에 매수 - 매도 3일 이하 3회 이상 , 0일은 노인정
        //
        return false;
    }

    @Override
    public String getTitle() {
        return "물가상승은 조상님이 내주냐?";
    }

}
