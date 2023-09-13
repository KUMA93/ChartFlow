package com.ssafy.chartflow.info.service.strategy;

import com.ssafy.chartflow.info.dto.UserGameDto;

public interface EmblemStrategy {
    public boolean checkCondition(UserGameDto userGameDto);

    public void getTitle(String title);
}
