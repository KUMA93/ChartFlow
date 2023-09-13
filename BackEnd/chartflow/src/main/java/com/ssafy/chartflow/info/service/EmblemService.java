package com.ssafy.chartflow.info.service;

import com.ssafy.chartflow.info.dto.UserGameDto;
import com.ssafy.chartflow.info.service.observer.EmblemObserver;
import com.ssafy.chartflow.info.service.observer.GameFinishObserver;
import com.ssafy.chartflow.info.service.observer.TurnFinishObserver;
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

    public void notifyObserver(UserGameDto userData) {
        for (EmblemObserver observer : observers) {
            if (userData.getType().equals("")&& observer instanceof TurnFinishObserver){
                observer.update(userData);
            }else if (userData.getType().equals("")&& observer instanceof GameFinishObserver){
                observer.update(userData);
            }
        }
    }

}
