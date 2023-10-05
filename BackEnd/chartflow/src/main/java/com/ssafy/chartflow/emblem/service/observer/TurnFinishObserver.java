package com.ssafy.chartflow.emblem.service.observer;

import com.ssafy.chartflow.emblem.dto.ResponseEmblemDto;
import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.service.UserEmblemService;
import com.ssafy.chartflow.emblem.service.strategy.turnstrategy.TurnStrategy;
import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.service.EmblemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class TurnFinishObserver implements EmblemObserver{
    private final List<TurnStrategy> turnStrategies;
    private final UserEmblemService userEmblemService;
    @Override
    public List<ResponseEmblemDto> update(UserGameDto userGameDto) {
        List<ResponseEmblemDto> emblems = new ArrayList<>();
        log.info("----------------turnstartegy---------------");
        for (TurnStrategy turnStrategy : turnStrategies){
            log.info("turnstrategy called = {}", turnStrategy.getClass());
            if (turnStrategy.checkCondition(userGameDto)){
                String emblemName = turnStrategy.getTitle();
                userEmblemService.saveEmblem(userGameDto, emblemName);
            }
        }
        return emblems;
    }
}
