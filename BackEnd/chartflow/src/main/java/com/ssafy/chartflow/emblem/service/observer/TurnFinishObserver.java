package com.ssafy.chartflow.emblem.service.observer;

import com.ssafy.chartflow.emblem.service.strategy.turnstrategy.TurnStrategy;
import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.service.EmblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TurnFinishObserver implements EmblemObserver{
    private final List<TurnStrategy> turnStrategies;
    private final EmblemService emblemService;
//    private final
    @Override
    public void update(UserGameDto userGameDto) {
        for (TurnStrategy turnStrategy : turnStrategies){
            if (turnStrategy.checkCondition(userGameDto)){
                String emblemName = turnStrategy.getTitle();
                emblemService.saveEmblem(userGameDto, emblemName);
            }
        }
    }
}
