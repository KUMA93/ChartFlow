package com.ssafy.chartflow.info.service;

import com.ssafy.chartflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoinService {
    private final UserRepository userRepository;

    public void decreaseCoin(long userId){
        userRepository.decreaseCoinByUserId(userId);
    }

    public void increaseCoin(long userId){
        userRepository.increaseCoinByUserId(userId);
    }

    public void increaAllCoin(){
        userRepository.increaseAll();
    }


}
