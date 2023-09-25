package com.ssafy.chartflow.emblem.service.strategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;

public class LowBoyStrategy implements GameStrategy{
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 매수&매도 없음
        // game turns테이블에 진행한 게임에 대한 값이 존재하지 않는 경우 return true
//        if(userGameDto.getGameTurns().length==0)
//            return true;
//        else
            return false;
    }

    @Override
    public String getTitle() {
        return "하남자";
    }
}
