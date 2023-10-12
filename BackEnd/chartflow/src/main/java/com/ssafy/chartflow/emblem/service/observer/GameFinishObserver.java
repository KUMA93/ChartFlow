package com.ssafy.chartflow.emblem.service.observer;

import com.ssafy.chartflow.emblem.dto.ResponseEmblemDto;
import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.service.UserEmblemService;
import com.ssafy.chartflow.emblem.service.strategy.gameStrategy.GameStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class GameFinishObserver implements EmblemObserver{
    private final List<GameStrategy> gameStrategies;
    private final UserEmblemService userEmblemService;
    //    private final
    @Override
    public List<ResponseEmblemDto> update(UserGameDto userGameDto) {
        List<ResponseEmblemDto> emblems = new ArrayList<>();
        for (GameStrategy gameStrategy : gameStrategies){
            if (gameStrategy.checkCondition(userGameDto)){
                String emblemName = gameStrategy.getTitle();
                emblems.add(userEmblemService.saveEmblem(userGameDto, emblemName));
            }
        }
        return emblems;
    }
}
