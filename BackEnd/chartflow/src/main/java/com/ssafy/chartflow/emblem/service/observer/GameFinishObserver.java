package com.ssafy.chartflow.emblem.service.observer;

import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.service.EmblemService;
import com.ssafy.chartflow.emblem.service.strategy.GameStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GameFinishObserver implements EmblemObserver{
    private final List<GameStrategy> gameStrategies;
    private final EmblemService emblemService;
    //    private final
    @Override
    public void update(UserGameDto userGameDto) {
        for (GameStrategy gameStrategy : gameStrategies){
            if (gameStrategy.checkCondition(userGameDto)){
                String emblemName = gameStrategy.getTitle();
                emblemService.saveEmblem(userGameDto, emblemName);
            }
        }
    }
}
