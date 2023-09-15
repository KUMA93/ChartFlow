package com.ssafy.chartflow.emblem.service.strategy;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.user.entity.User;

public interface EmblemStrategy {
    public boolean checkCondition(UserGameDto userGameDto);

    public String getTitle();
}
