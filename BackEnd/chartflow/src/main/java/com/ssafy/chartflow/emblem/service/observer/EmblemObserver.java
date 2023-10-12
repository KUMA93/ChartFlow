package com.ssafy.chartflow.emblem.service.observer;

import com.ssafy.chartflow.emblem.dto.ResponseEmblemDto;
import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.entity.Emblem;

import java.util.List;

public interface EmblemObserver{
    public abstract List<ResponseEmblemDto> update(UserGameDto userGameDto);
}
