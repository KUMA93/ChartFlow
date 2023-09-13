package com.ssafy.chartflow.emblem.service.observer;

import com.ssafy.chartflow.emblem.dto.UserGameDto;

public interface EmblemObserver{
    public abstract void update(UserGameDto userGameDto);
}
