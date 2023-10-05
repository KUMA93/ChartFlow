package com.ssafy.chartflow.emblem.service;

import com.ssafy.chartflow.emblem.entity.Emblem;
import com.ssafy.chartflow.emblem.entity.UserEmblem;
import com.ssafy.chartflow.emblem.repository.EmblemRepository;
import com.ssafy.chartflow.emblem.repository.UserEmblemRepository;
import com.ssafy.chartflow.emblem.service.observer.EmblemObserver;
import com.ssafy.chartflow.emblem.service.observer.GameFinishObserver;
import com.ssafy.chartflow.emblem.service.observer.TurnFinishObserver;
import com.ssafy.chartflow.emblem.dto.UserGameDto;
import com.ssafy.chartflow.emblem.service.strategy.EmblemStrategy;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmblemService {
    private final List<EmblemObserver> observers;

    public void addObserver(EmblemObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(EmblemObserver observer) {
        observers.remove(observer);
    }

    public void notifyObserver(UserGameDto userGameDto, int flag) {
        List<UserEmblem> emblems = userGameDto.getUser().getEmblems();
        userGameDto.setEmblems(emblems);
        for (EmblemObserver observer: observers){
            log.info("type = {}", observer.getClass());

        }
        for (EmblemObserver observer : observers){
            if (flag == 0 && observer instanceof TurnFinishObserver){
                log.info("-------------turnobserver update called -------------");
                observer.update(userGameDto);
            }else if (flag == 1 && observer instanceof GameFinishObserver){
                observer.update(userGameDto);
            }
        }
    }



}
