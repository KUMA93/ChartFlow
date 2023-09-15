package com.ssafy.chartflow.emblem.service.strategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;

public class JosangStrategy implements GameStrategy{
    @Override
    public boolean checkCondition(UserGameDto userGameDto) {
        // 게임 종료 후 매수매도하고도 수익률 0프로대
        // GameHistory테이블에 진행한 게임에 대한 rate값이 0점대이면 return true
        if(userGameDto.getRate()>-1 && userGameDto.getRate()<1)
            return true;
        else
            return false;
    }

    @Override
    public String getTitle() {
        return "물가상승은 조상님이 내주냐?";
    }

}
