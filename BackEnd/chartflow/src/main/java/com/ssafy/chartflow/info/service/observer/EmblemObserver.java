package com.ssafy.chartflow.info.service.observer;

import com.ssafy.chartflow.info.dto.UserGameDto;

public abstract class EmblemObserver{
    public abstract void update(UserGameDto userGameDto);
}
