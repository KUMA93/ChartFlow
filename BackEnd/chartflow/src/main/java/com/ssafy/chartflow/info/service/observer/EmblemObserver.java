package com.ssafy.chartflow.info.service.observer;

import com.ssafy.chartflow.info.dto.UserGameDto;

public interface EmblemObserver{
    public abstract void update(UserGameDto userGameDto);
}
